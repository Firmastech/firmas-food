package danieldjgomes.larica.app.usecase.usuario.exceptions;

public class RestauranteNaoEncontradoException extends RuntimeException {

    public RestauranteNaoEncontradoException() {
        super("O restaurante buscado não foi encontrado");
    }
}
