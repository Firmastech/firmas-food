package danieldjgomes.larica.usecase.restaurante.exceptions;

public class RestauranteNotFoundException extends RuntimeException{

    public RestauranteNotFoundException(String message) {
        super(message);
    }

}
