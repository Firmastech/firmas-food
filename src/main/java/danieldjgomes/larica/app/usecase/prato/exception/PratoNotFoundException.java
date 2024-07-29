package danieldjgomes.larica.app.usecase.prato.exception;

public class PratoNotFoundException extends RuntimeException{

    public PratoNotFoundException(){
        super("Prato não encontrado");
    }
}
