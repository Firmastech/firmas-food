package danieldjgomes.larica.app.usecase.categoria.exception;

public class CategoriaNotFoundException extends RuntimeException{

    public CategoriaNotFoundException(){
        super("Categoria não encontrado ou desativada");
    }
}
