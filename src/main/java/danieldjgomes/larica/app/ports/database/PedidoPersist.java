package danieldjgomes.larica.app.ports.database;

import danieldjgomes.larica.app.usecase.pedido.request.ProcessarPedidoRequest;

import java.util.UUID;

public interface PedidoPersist {

    void createPedido(ProcessarPedidoRequest pedido);

    ProcessarPedidoRequest getPedidoById(UUID pedidoId);

    void atualizarPedidoById(ProcessarPedidoRequest pedido);
}
