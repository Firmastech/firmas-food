package danieldjgomes.larica.app.adapter.database.pedidos.impl;

import danieldjgomes.larica.app.adapter.database.pedidos.model.ItemPedidoEntity;
import danieldjgomes.larica.app.adapter.database.pedidos.model.PedidoEntity;
import danieldjgomes.larica.app.adapter.database.pedidos.repository.PedidoRepository;
import danieldjgomes.larica.app.ports.database.PedidoPersist;
import danieldjgomes.larica.app.usecase.pedido.exceptions.CreatePedidoException;
import danieldjgomes.larica.app.usecase.pedido.exceptions.ParseEntityException;
import danieldjgomes.larica.app.usecase.pedido.exceptions.PedidoNaoEncontradoException;
import danieldjgomes.larica.app.usecase.pedido.request.PratosRequestList;
import danieldjgomes.larica.app.usecase.pedido.request.ProcessarPedidoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoPersistImpl implements PedidoPersist {
    private final PedidoRepository pedidoRepository;

    @Override
    public void createPedido(ProcessarPedidoRequest pedido) {
        try {
            pedidoRepository.save(toPedidoEntity(pedido));
        } catch (Exception e) {
            throw new CreatePedidoException(e);
        }
    }

    @Override
    public ProcessarPedidoRequest getPedidoById(UUID pedidoId) {
        Optional<PedidoEntity> optionalPedidoEntity = pedidoRepository.findById(pedidoId);
        if (optionalPedidoEntity.isPresent()) {
            return toProcessarPedidoRequest(optionalPedidoEntity.get())
                    .orElseThrow(ParseEntityException::new);
        } else {
            throw new PedidoNaoEncontradoException();
        }
    }

    @Override
    public void atualizarPedidoById(ProcessarPedidoRequest pedido) {
        PedidoEntity pedidoEntity = pedidoRepository.findById(UUID.fromString(pedido.getId().toString()))
                .orElseThrow(PedidoNaoEncontradoException::new);
        pedidoEntity.setStatus("PEDIDO_CRIADO");
        pedidoRepository.save(pedidoEntity);
    }

    public Optional<ProcessarPedidoRequest> toProcessarPedidoRequest(PedidoEntity pedidoEntity) {
        if (pedidoEntity == null) {
            return Optional.empty();
        }
        return Optional.of(ProcessarPedidoRequest.builder()
                .id(UUID.fromString(pedidoEntity.getId()))
                .usuarioId(pedidoEntity.getUsuarioId())
                .itensList(toItemPedidoRequestList(pedidoEntity.getItens()))
                .build());

    }

    public List<PratosRequestList> toItemPedidoRequestList(List<ItemPedidoEntity> itensPedidoDB) {
        return itensPedidoDB.stream()
                .map(itemPedidoEntity -> PratosRequestList.builder()
                        .pratoId(UUID.fromString(itemPedidoEntity.getPratoId()))
                        .quantidade(itemPedidoEntity.getQuantidade())
                        .build())
                .collect(Collectors.toList());
    }

    public PedidoEntity toPedidoEntity(ProcessarPedidoRequest pedidoRequest) {
        if (pedidoRequest == null) {
            return null;
        }
        List<ItemPedidoEntity> itensPedidoDB = toItemPedidoEntityList(pedidoRequest.getItensList());
        return PedidoEntity.builder()
                .id(pedidoRequest.getId().toString())
                .usuarioId(pedidoRequest.getUsuarioId())
                .dataHoraPedido(LocalDateTime.now())
                .status("PEDIDO_CRIADO")
                .itens(itensPedidoDB)
                .build();
    }

    public List<ItemPedidoEntity> toItemPedidoEntityList(List<PratosRequestList> itensPedidoRequestList) {
        if (itensPedidoRequestList == null) {
            return null;
        }
        return itensPedidoRequestList.stream()
                .map(itemPedidoRequest -> ItemPedidoEntity.builder()
                        .pratoId(itemPedidoRequest.getPratoId().toString())
                        .quantidade(itemPedidoRequest.getQuantidade())
                        .build())
                .collect(Collectors.toList());
    }
}