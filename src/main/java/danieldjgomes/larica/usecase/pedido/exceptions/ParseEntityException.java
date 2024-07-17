package danieldjgomes.larica.usecase.pedido.exceptions;

public class ParseEntityException extends RuntimeException{
    public ParseEntityException() {
        super("Erro ao converter a entidade");
    }
}
