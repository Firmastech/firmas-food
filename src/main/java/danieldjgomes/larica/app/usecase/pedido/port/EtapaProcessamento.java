package danieldjgomes.larica.app.usecase.pedido.port;

import danieldjgomes.larica.app.usecase.pedido.request.ProcessarPedidoRequest;

public interface EtapaProcessamento {
    boolean processar(ProcessarPedidoRequest pedido);
}
