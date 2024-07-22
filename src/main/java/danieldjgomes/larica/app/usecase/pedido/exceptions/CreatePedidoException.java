package danieldjgomes.larica.app.usecase.pedido.exceptions;

public class CreatePedidoException extends RuntimeException {
    public CreatePedidoException( Throwable trace) {
        super("Erro ao salvar pedido no banco de dados", trace);
    }
}