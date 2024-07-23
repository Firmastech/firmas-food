package danieldjgomes.larica.core.usecases;

import danieldjgomes.larica.core.cardapio.dtos.response.CardapioResponseDTO;

import java.util.List;

public interface CardapioPratoUseCase {

    void addPratosToCardapio(String cardapioId, List<String> pratoIds);

    CardapioResponseDTO getCardapioById(String cardapioId);

    void removePratosFromCardapio(String cardapioId, List<String> pratoIds);

}
