package danieldjgomes.larica.app.usecase.restaurante.exceptions;

public class RestauranteNomeUtilizadoException extends RuntimeException{

    public RestauranteNomeUtilizadoException() {
        super("Nome já Utilizado");
    }
}
