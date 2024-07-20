package danieldjgomes.larica.app.usecase.pedido;

import danieldjgomes.larica.app.adapter.database.pedidos.impl.PedidoPersistImpl;
import danieldjgomes.larica.app.usecase.pedido.port.EtapaProcessamento;
import danieldjgomes.larica.app.usecase.pedido.request.ProcessarPedidoRequest;

public class ValidarPedidoUsecase implements EtapaProcessamento {
    public ValidarPedidoUsecase(PedidoPersistImpl pedidoPersistImpl) {
    }
    @Override
    public boolean processar(ProcessarPedidoRequest pedido) {

        /*
        Verificar se todos os campos necessários estão presentes no pedido. Isso pode incluir
        o ID do usuário, o ID do restaurante, a lista de itens do pedido, etc.
        (talvez não seja necessário, pois o pedido já foi validado no controller)

        Verificar se os valores dos campos são válidos. Verificar
        se a quantidade de cada item do pedido é maior que zero, se o total do pedido é
        correto, etc.

        Verificar se o pedido é consistente. Verificar se todos os itens do pedido pertencem
        ao mesmo restaurante.
        */
        return true;
    }
}
