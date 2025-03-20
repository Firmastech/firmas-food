package danieldjgomes.larica.app.usecase.token.usecase.impl;

public class RevalidarTokenInvalidoException extends RuntimeException {
    public RevalidarTokenInvalidoException() {
        super("Token inválido ou expirado");
    }
}
