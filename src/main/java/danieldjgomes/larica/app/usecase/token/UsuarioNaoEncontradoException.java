package danieldjgomes.larica.app.usecase.token;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException() {
        super("Par usuario-restaurante nao foi encontrado");
    }
}
