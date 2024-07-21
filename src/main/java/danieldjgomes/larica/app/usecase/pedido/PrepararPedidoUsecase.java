package danieldjgomes.larica.app.usecase.pedido;

import danieldjgomes.larica.app.adapter.database.pedidos.impl.PedidoPersistImpl;
import danieldjgomes.larica.app.usecase.pedido.port.EtapaProcessamento;
import danieldjgomes.larica.app.usecase.pedido.request.ProcessarPedidoRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PrepararPedidoUsecase implements EtapaProcessamento {
    private final PedidoPersistImpl pedidoPersistImpl;
    @Override
    public boolean processar(ProcessarPedidoRequest pedido) {

        /*
        Alterar o status do pedido para "PREPARANDO".

        Verificar se todos os itens do pedido estão disponíveis e se o restaurante tem
        capacidade para preparar o pedido.

        comunicar-se com outros sistemas ou serviços para iniciar o
        processo de preparação do pedido.
         */

        return true;
    }
}
