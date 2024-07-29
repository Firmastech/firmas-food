package danieldjgomes.larica.app.usecase.restaurante.exceptions;

public class RestauranteNomeInvalidoException extends RuntimeException{

    public RestauranteNomeInvalidoException(String message) {
        super(message);
    }
}
