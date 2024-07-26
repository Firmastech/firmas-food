package danieldjgomes.larica.core.usecases;

import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;

import java.util.List;

public interface CardapioPratoUseCase {

    void addPratosToCardapio(String cardapioId, List<String> pratoIds);

    CardapioResponse getCardapioById(String cardapioId);

    void removePratosFromCardapio(String cardapioId, List<String> pratoIds);

}
