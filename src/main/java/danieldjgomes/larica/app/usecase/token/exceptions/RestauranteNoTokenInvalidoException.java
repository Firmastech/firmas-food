package danieldjgomes.larica.app.usecase.token.exceptions;

public class RestauranteNoTokenInvalidoException extends RuntimeException {

    public RestauranteNoTokenInvalidoException(Exception e) {
        super(e);
    }
}
