package danieldjgomes.larica.app.usecase.usuario.exceptions;

public class  CriandoUsuarioDuplicadoException extends RuntimeException {

    public CriandoUsuarioDuplicadoException() {
        super("O usuario ja existe no sistema.");
    }
}
