package danieldjgomes.larica.usecase.pedido.port;

import danieldjgomes.larica.usecase.pedido.request.ProcessarPedidoRequest;

public interface EtapaProcessamento {
    void processar(ProcessarPedidoRequest pedido);
}
