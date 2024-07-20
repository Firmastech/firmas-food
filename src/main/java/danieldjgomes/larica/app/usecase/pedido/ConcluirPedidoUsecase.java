package danieldjgomes.larica.app.usecase.pedido;

import danieldjgomes.larica.app.adapter.database.pedidos.impl.PedidoPersistImpl;
import danieldjgomes.larica.app.usecase.pedido.port.EtapaProcessamento;
import danieldjgomes.larica.app.usecase.pedido.request.ProcessarPedidoRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConcluirPedidoUsecase implements EtapaProcessamento {
    private final PedidoPersistImpl pedidoPersistImpl;
    @Override
    public boolean processar(ProcessarPedidoRequest pedido) {

        /*
        Alterar o status do pedido para "CONCLUÍDO".

        Atualizar a hora de conclusão do pedido.

        Se necessário, comunicar-se com outros sistemas
        ou serviços para informar que o pedido foi concluído.
         */
        return true;
    }
}
