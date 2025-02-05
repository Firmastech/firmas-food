package danieldjgomes.larica.core.usecases;

import danieldjgomes.larica.core.cardapio.dtos.request.AddPratosToCardapioRequest;
import danieldjgomes.larica.core.cardapio.dtos.response.CardapioResponseDTO;

public interface CardapioPratoUseCase {

    CardapioResponseDTO adicionarPratos(String cardapioId, AddPratosToCardapioRequest request);

     void removerPrato (String cardapioId, String pratoId);
}
