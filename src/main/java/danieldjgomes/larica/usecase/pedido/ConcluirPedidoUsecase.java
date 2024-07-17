package danieldjgomes.larica.usecase.pedido;

import danieldjgomes.larica.adapter.database.pedidos.impl.PedidoPersistImpl;
import danieldjgomes.larica.usecase.pedido.request.ProcessarPedidoRequest;
import danieldjgomes.larica.usecase.pedido.port.EtapaProcessamento;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConcluirPedidoUsecase implements EtapaProcessamento {
    private final PedidoPersistImpl pedidoPersistImpl;
    @Override
    public void processar(ProcessarPedidoRequest pedido) {

    }
}
