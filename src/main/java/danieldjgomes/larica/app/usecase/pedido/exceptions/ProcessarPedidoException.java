package danieldjgomes.larica.app.usecase.pedido.exceptions;

public class ProcessarPedidoException extends RuntimeException {
    public ProcessarPedidoException() {
        super("Falha ao processar o pedido");
    }
}