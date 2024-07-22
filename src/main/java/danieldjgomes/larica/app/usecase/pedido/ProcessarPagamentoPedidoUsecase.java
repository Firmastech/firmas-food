package danieldjgomes.larica.app.usecase.pedido;

import danieldjgomes.larica.app.adapter.database.pedidos.impl.PedidoPersistImpl;
import danieldjgomes.larica.app.usecase.pedido.port.EtapaProcessamento;
import danieldjgomes.larica.app.usecase.pedido.request.ProcessarPedidoRequest;

public class ProcessarPagamentoPedidoUsecase implements EtapaProcessamento {

    //TODO: quando for implementar o fluxo de pagamentos, mover para a
    // estrutura de pastas correta.

    public ProcessarPagamentoPedidoUsecase(PedidoPersistImpl pedidoPersistImpl) {

    }

    @Override
    public boolean processar(ProcessarPedidoRequest pedido) {
        return true;
    }
}
