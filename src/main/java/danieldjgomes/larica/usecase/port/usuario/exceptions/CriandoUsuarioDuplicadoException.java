package danieldjgomes.larica.usecase.port.usuario.exceptions;

public class CriandoUsuarioDuplicadoException extends RuntimeException {

    public CriandoUsuarioDuplicadoException() {
        super("O usuario ja existe no sistema.");
    }
}
