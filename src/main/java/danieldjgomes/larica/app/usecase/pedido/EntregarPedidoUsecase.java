package danieldjgomes.larica.app.usecase.pedido;

import danieldjgomes.larica.app.adapter.database.pedidos.impl.PedidoPersistImpl;
import danieldjgomes.larica.app.usecase.pedido.port.EtapaProcessamento;
import danieldjgomes.larica.app.usecase.pedido.request.ProcessarPedidoRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EntregarPedidoUsecase implements EtapaProcessamento {
    private final PedidoPersistImpl pedidoPersistImpl;
    @Override
    public boolean processar(ProcessarPedidoRequest pedido) {


        /*
        Alterar o status do pedido para "EM ENTREGA".

        Comunicar-se com outros sistemas ou serviços para iniciar o processo de
        entrega do pedido, como um serviço de logística ou de entrega.

        Atualizar a hora estimada de entrega com base na localização do cliente e
        na disponibilidade do serviço de entrega.
         */

        return true;
    }
}
