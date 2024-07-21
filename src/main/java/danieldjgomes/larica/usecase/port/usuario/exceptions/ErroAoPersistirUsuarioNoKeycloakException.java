package danieldjgomes.larica.usecase.port.usuario.exceptions;

public class ErroAoPersistirUsuarioNoKeycloakException extends RuntimeException {

    public ErroAoPersistirUsuarioNoKeycloakException() {
        super("Ocorreu um erro ao persistir o usuario no Keycloak");
    }
}
