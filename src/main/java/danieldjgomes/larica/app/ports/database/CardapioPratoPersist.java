package danieldjgomes.larica.app.ports.database;

import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;

import java.util.List;

public interface CardapioPratoPersist {

    void addPratosToCardapio(String cardapioId, List<String> pratoIds);

    CardapioResponse getCardapioById(String cardapioId);

    void removePratosFromCardapio(String cardapioId, List<String> pratoIds);
}
