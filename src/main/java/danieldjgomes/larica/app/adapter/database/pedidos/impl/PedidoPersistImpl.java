package danieldjgomes.larica.app.adapter.database.pedidos.impl;

import danieldjgomes.larica.app.adapter.database.pedidos.model.ItemPedidoEntity;
import danieldjgomes.larica.app.adapter.database.pedidos.model.PedidoEntity;
import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.adapter.database.pedidos.repository.PedidoRepository;
import danieldjgomes.larica.app.adapter.database.pedidos.repository.RestauranteRepositoryMock;
import danieldjgomes.larica.app.adapter.database.pedidos.repository.UsuarioRepository;
import danieldjgomes.larica.app.ports.database.PedidoPersist;
import danieldjgomes.larica.app.usecase.pedido.exceptions.PedidoNaoEncontradoException;
import danieldjgomes.larica.app.usecase.pedido.request.PratosRequestList;
import danieldjgomes.larica.app.usecase.pedido.request.ProcessarPedidoRequest;
import danieldjgomes.larica.dataprovider.repository.entity.RestauranteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoPersistImpl implements PedidoPersist {
    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final RestauranteRepositoryMock restauranteRepository;

    @Override
    public void postPedido(ProcessarPedidoRequest pedido) {
        try {
            PedidoEntity pedidoEntity = convertRequestToEntity(pedido);
            pedidoRepository.save(pedidoEntity);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = e.getMostSpecificCause().getMessage();
            String fieldName = extractFieldNameFromErrorMessage(errorMessage);
            throw new RuntimeException("Falha ao postar pedido. Campo com problema: " + fieldName, e);
        } catch (Exception e) {
            // Registre o erro aqui
            throw new RuntimeException("Falha ao postar pedido", e);
        }
    }

    @Override
    public ProcessarPedidoRequest getPedidoById(UUID pedidoId) {
        PedidoEntity pedidoEntity = findPedidoEntityById(pedidoId.toString());
        return convertEntityToRequest(pedidoEntity);
    }

    @Override
    public void atualizarPedidoById(ProcessarPedidoRequest pedido) {

    }

    private PedidoEntity convertRequestToEntity(ProcessarPedidoRequest pedidoRequest) {
        UsuarioEntity usuarioEntity = findUsuarioEntityById(pedidoRequest.getUsuarioId());
        Optional<RestauranteEntity> restauranteId = restauranteRepository.findById(pedidoRequest.getItensList().get(0).getRestauranteId());

        PedidoEntity pedidoEntity = PedidoEntity.builder()
                .id(UUID.randomUUID().toString())
                .usuario(usuarioEntity)
                .restauranteId(restauranteId.map(RestauranteEntity::getId).orElse(null))
                .dataHoraPedido(LocalDateTime.now())
                .status("PEDIDO_CRIADO")
                .valor(BigDecimal.ONE)
                .build();
        List<ItemPedidoEntity> itensPedidoDB = convertRequestItemsToEntityItems(pedidoEntity, pedidoRequest.getItensList());
        pedidoEntity.setItens(itensPedidoDB);
        return pedidoEntity;
    }

    private List<ItemPedidoEntity> convertRequestItemsToEntityItems(PedidoEntity pedidoEntity, List<PratosRequestList> itensPedidoRequestList) {
        return itensPedidoRequestList.stream()
                .map(itemPedidoRequest -> ItemPedidoEntity.builder()
                        .id(UUID.randomUUID().toString())
                        .pratoId(itemPedidoRequest.getPratoId())
                        .pedidoId(pedidoEntity)
                        .quantidade(itemPedidoRequest.getQuantidade())
                        .observacao(itemPedidoRequest.getDescricao())
                        .build())
                .collect(Collectors.toList());
    }

    private UsuarioEntity findUsuarioEntityById(String usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    private PedidoEntity findPedidoEntityById(String pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new PedidoNaoEncontradoException());
    }

    private ProcessarPedidoRequest convertEntityToRequest(PedidoEntity pedidoEntity) {
        // Implementação do método de conversão
        return null;}

    private String extractFieldNameFromErrorMessage(String errorMessage) {
        String prefix = "Column: ";
        int index = errorMessage.indexOf(prefix);

        if (index != -1) {
            return errorMessage.substring(index + prefix.length());
        }

        return "Campo desconhecido";
    }
}