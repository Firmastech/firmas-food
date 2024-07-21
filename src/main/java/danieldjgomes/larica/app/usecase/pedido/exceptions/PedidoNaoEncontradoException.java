package danieldjgomes.larica.app.usecase.pedido.exceptions;

public class PedidoNaoEncontradoException extends RuntimeException{
    public PedidoNaoEncontradoException() {
        super("Pedido não encontrado");
    }
}
