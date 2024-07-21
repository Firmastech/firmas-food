package danieldjgomes.larica.app.adapter.database.pedidos.impl;

import danieldjgomes.larica.app.adapter.database.pedidos.model.ItemPedidoEntity;
import danieldjgomes.larica.app.adapter.database.pedidos.model.PedidoEntity;
import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.adapter.database.pedidos.repository.PedidoRepository;
import danieldjgomes.larica.app.adapter.database.pedidos.repository.UsuarioRepository;
import danieldjgomes.larica.app.ports.database.PedidoPersist;
import danieldjgomes.larica.app.usecase.pedido.exceptions.PedidoNaoEncontradoException;
import danieldjgomes.larica.app.usecase.pedido.request.PratosRequestList;
import danieldjgomes.larica.app.usecase.pedido.request.ProcessarPedidoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoPersistImpl implements PedidoPersist {
    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public void createPedido(ProcessarPedidoRequest pedido) {
        PedidoEntity pedidoEntity = convertRequestToEntity(pedido);
        pedidoRepository.save(pedidoEntity);
    }

    @Override
    public ProcessarPedidoRequest getPedidoById(UUID pedidoId) {
        PedidoEntity pedidoEntity = findPedidoEntityById(pedidoId.toString());
        return convertEntityToRequest(pedidoEntity);
    }

    @Override
    public void atualizarPedidoById(ProcessarPedidoRequest pedido) {
        PedidoEntity pedidoEntity = findPedidoEntityById(pedido.getId());
        pedidoEntity.setStatus("PEDIDO_CRIADO");
        pedidoRepository.save(pedidoEntity);
    }

    private PedidoEntity convertRequestToEntity(ProcessarPedidoRequest pedidoRequest) {
        List<ItemPedidoEntity> itensPedidoDB = convertRequestItemsToEntityItems(pedidoRequest.getItensList());
        //UsuarioEntity usuarioEntity = findUsuarioEntityById(pedidoRequest.getUsuarioId());
        pedidoRequest.setId(UUID.randomUUID().toString());
        return PedidoEntity.builder()
                .id(pedidoRequest.getId())
                .usuarioId(null)
                .dataHoraPedido(LocalDateTime.now())
                .status("PEDIDO_CRIADO")
                .itens(itensPedidoDB)
                .build();
    }

    private List<ItemPedidoEntity> convertRequestItemsToEntityItems(List<PratosRequestList> itensPedidoRequestList) {
        return itensPedidoRequestList.stream()
                .map(itemPedidoRequest -> ItemPedidoEntity.builder()
                        .pratoId(itemPedidoRequest.getPratoId())
                        .quantidade(itemPedidoRequest.getQuantidade())
                        .build())
                .collect(Collectors.toList());
    }

    private UsuarioEntity findUsuarioEntityById(String id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    private PedidoEntity findPedidoEntityById(String id) {
        return pedidoRepository.findById(id)
                .orElseThrow(PedidoNaoEncontradoException::new);
    }

    private ProcessarPedidoRequest convertEntityToRequest(PedidoEntity pedidoEntity) {
        return ProcessarPedidoRequest.builder()
                .id(String.valueOf(pedidoEntity.getId()))
                .usuarioId(pedidoEntity.getUsuarioId().getId())
                .itensList(convertEntityItemsToRequestItems(pedidoEntity.getItens()))
                .build();
    }

    private List<PratosRequestList> convertEntityItemsToRequestItems(List<ItemPedidoEntity> itensPedidoDB) {
        return itensPedidoDB.stream()
                .map(itemPedidoEntity -> PratosRequestList.builder()
                        .pratoId(itemPedidoEntity.getPratoId())
                        .quantidade(itemPedidoEntity.getQuantidade())
                        .build())
                .collect(Collectors.toList());
    }
}