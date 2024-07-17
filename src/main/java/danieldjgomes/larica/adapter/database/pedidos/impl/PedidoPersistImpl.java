package danieldjgomes.larica.adapter.database.pedidos.impl;

import danieldjgomes.larica.adapter.database.pedidos.model.ItemPedidoEntityList;
import danieldjgomes.larica.adapter.database.pedidos.model.PedidoEntity;
import danieldjgomes.larica.adapter.database.pedidos.repository.PedidoRepository;
import danieldjgomes.larica.usecase.pedido.exceptions.CreatePedidoException;
import danieldjgomes.larica.usecase.pedido.exceptions.ParseEntityException;
import danieldjgomes.larica.usecase.pedido.request.ItemPedidoRequestList;
import danieldjgomes.larica.usecase.pedido.request.ProcessarPedidoRequest;
import danieldjgomes.larica.ports.database.PedidoPersist;
import danieldjgomes.larica.usecase.pedido.exceptions.PedidoNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        PedidoEntity pedidoEntity = pedidoRepository.findById(pedido.getId())
                .orElseThrow(PedidoNaoEncontradoException::new);
        pedidoEntity.setStatus(pedido.getStatus());
        pedidoRepository.save(pedidoEntity);
    }

    public Optional<ProcessarPedidoRequest> toProcessarPedidoRequest(PedidoEntity pedidoEntity) {
        if (pedidoEntity == null) {
            return Optional.empty();
        }
        return Optional.of(ProcessarPedidoRequest.builder()
                .id(pedidoEntity.getId())
                .status(pedidoEntity.getStatus())
                .dataHoraEntrega(pedidoEntity.getDataHoraEntrega())
                .dataHoraPedido(pedidoEntity.getDataHoraPedido())
                .total(pedidoEntity.getTotal())
                .itensList(toItemPedidoRequestList(pedidoEntity.getItens()))
                .build());

    }

    public List<ItemPedidoRequestList> toItemPedidoRequestList(List<ItemPedidoEntityList> itensPedidoDB) {
        return itensPedidoDB.stream()
                .map(itemPedidoEntityList -> ItemPedidoRequestList.builder()
                        .id(itemPedidoEntityList.getId())
                        .descricao(itemPedidoEntityList.getDescricao())
                        .pratoId(itemPedidoEntityList.getPratoId())
                        .quantidade(itemPedidoEntityList.getQuantidade())
                        .build())
                .collect(Collectors.toList());
    }

    public PedidoEntity toPedidoEntity(ProcessarPedidoRequest pedidoRequest) {
        if (pedidoRequest == null) {
            return null;
        }
        List<ItemPedidoEntityList> itensPedidoDB = toItemPedidoEntityList(pedidoRequest.getItensList());
        return PedidoEntity.builder()
                .id(pedidoRequest.getId())
                .status(pedidoRequest.getStatus())
                .dataHoraEntrega(pedidoRequest.getDataHoraEntrega())
                .dataHoraPedido(pedidoRequest.getDataHoraPedido())
                .total(pedidoRequest.getTotal())
                .itens(itensPedidoDB)
                .build();
    }

    public List<ItemPedidoEntityList> toItemPedidoEntityList(List<ItemPedidoRequestList> itensPedidoRequestList) {
        if (itensPedidoRequestList == null) {
            return null;
        }
        return itensPedidoRequestList.stream()
                .map(itemPedidoRequest -> ItemPedidoEntityList.builder()
                        .id(itemPedidoRequest.getId())
                        .descricao(itemPedidoRequest.getDescricao())
                        .pratoId(itemPedidoRequest.getPratoId())
                        .quantidade(itemPedidoRequest.getQuantidade())
                        .build())
                .collect(Collectors.toList());
    }
}
