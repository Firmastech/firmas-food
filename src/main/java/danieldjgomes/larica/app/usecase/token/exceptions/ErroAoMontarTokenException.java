package danieldjgomes.larica.app.usecase.token.exceptions;

public class ErroAoMontarTokenException extends RuntimeException {
    public ErroAoMontarTokenException(Exception e) {
        super(e);
    }
}
