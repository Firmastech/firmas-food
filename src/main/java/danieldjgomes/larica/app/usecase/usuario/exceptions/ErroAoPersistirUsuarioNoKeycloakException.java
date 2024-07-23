package danieldjgomes.larica.app.usecase.usuario.exceptions;

public class ErroAoPersistirUsuarioNoKeycloakException extends RuntimeException {

    public ErroAoPersistirUsuarioNoKeycloakException() {
        super("Ocorreu um erro ao persistir o usuario no Keycloak");
    }
}
