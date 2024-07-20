package danieldjgomes.larica.app.usecase.pedido.port;

import danieldjgomes.larica.app.usecase.pedido.request.ProcessarPedidoRequest;

public interface ProcessarPedidoUsecase {
    void processarPedido(ProcessarPedidoRequest pedido);
}
