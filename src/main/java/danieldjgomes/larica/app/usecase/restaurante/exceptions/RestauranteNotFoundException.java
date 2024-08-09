package danieldjgomes.larica.app.usecase.restaurante.exceptions;

public class RestauranteNotFoundException extends RuntimeException{

    public RestauranteNotFoundException() {
        super("Restaurante nao encontrado");
    }

}
