package danieldjgomes.larica.app.usecase.token.exceptions;

public class EmailNoTokenInvalidoException extends RuntimeException {

    public EmailNoTokenInvalidoException(Exception e) {
        super(e);
    }
}
