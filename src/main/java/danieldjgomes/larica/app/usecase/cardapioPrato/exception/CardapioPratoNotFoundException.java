package danieldjgomes.larica.app.usecase.cardapioPrato.exception;

public class CardapioPratoNotFoundException extends RuntimeException{

    public CardapioPratoNotFoundException(){
        super("Cardapio ou Prato não encontrado");
    }
}
