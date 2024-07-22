package danieldjgomes.larica.app.ports.database;

import danieldjgomes.larica.app.usecase.pedido.request.ProcessarPedidoRequest;
import danieldjgomes.larica.app.usecase.pedido.response.ProcessarPedidoResponse;

import java.util.UUID;

public interface PedidoPersist {

    void postPedido(ProcessarPedidoRequest pedido);

    ProcessarPedidoResponse getPedidoById(String pedidoId);

    void atualizarPedidoById(ProcessarPedidoRequest pedido);
}
