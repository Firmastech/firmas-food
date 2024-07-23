package danieldjgomes.larica.app.usecase.pedido;

import danieldjgomes.larica.app.adapter.database.pedidos.impl.PedidoPersistImpl;
import danieldjgomes.larica.app.usecase.pedido.request.ProcessarPedidoRequest;
import danieldjgomes.larica.app.usecase.pedido.port.EtapaProcessamento;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VerificarDisponibilidadeUsecase implements EtapaProcessamento {
    private final PedidoPersistImpl pedidoPersistImpl;
    @Override
    public boolean processar(ProcessarPedidoRequest pedido) {
        /*
         Verificar se todos os itens do pedido estão disponíveis.
         Verificar se o restaurante está aberto e aceitando pedidos.
         Verificar se o usuário tem saldo suficiente para fazer o pedido.
         */

        return true;
    }
}
