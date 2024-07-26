package danieldjgomes.larica.app.usecase.cardapio;

import danieldjgomes.larica.app.usecase.cardapio.request.AtualizarDescritivosCardapioRequest;
import danieldjgomes.larica.app.usecase.cardapio.response.AtualizarCardapioResponse;

public interface AtualizarDescritivoCardapioUseCase {

    AtualizarCardapioResponse atualizarCardapio(String cardapioId, AtualizarDescritivosCardapioRequest atualizarDescritivosCardapioRequest, String restauranteId);
}