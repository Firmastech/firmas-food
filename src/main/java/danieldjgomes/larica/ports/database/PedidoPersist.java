package danieldjgomes.larica.ports.database;

import danieldjgomes.larica.adapter.database.pedidos.model.PedidoEntity;
import danieldjgomes.larica.usecase.pedido.request.ProcessarPedidoRequest;

import java.util.UUID;

public interface PedidoPersist {

    void createPedido(ProcessarPedidoRequest pedido);

    ProcessarPedidoRequest getPedidoById(UUID pedidoId);

    void atualizarPedidoById(ProcessarPedidoRequest pedido);
}
