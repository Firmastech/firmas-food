package danieldjgomes.larica.app.usecase.cardapio.exception;

public class CardapioNotFoundException extends RuntimeException{

    public CardapioNotFoundException(){
        super("Cardapio não encontrado");
    }
}
