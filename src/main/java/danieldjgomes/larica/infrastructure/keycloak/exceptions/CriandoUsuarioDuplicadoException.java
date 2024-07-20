package danieldjgomes.larica.infrastructure.keycloak.exceptions;

public class CriandoUsuarioDuplicadoException extends RuntimeException {

    public CriandoUsuarioDuplicadoException() {
        super("O usuario ja existe no sistema, nao pode ser criado");
    }
}
