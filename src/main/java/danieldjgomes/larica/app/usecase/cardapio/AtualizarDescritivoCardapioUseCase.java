package danieldjgomes.larica.app.usecase.cardapio;

import danieldjgomes.larica.app.usecase.cardapio.response.AtualizarCardapioResponse;
import danieldjgomes.larica.app.usecase.cardapio.request.AtualizarDescritivosCardapioRequest;

public interface AtualizarDescritivoCardapioUseCase {

    AtualizarCardapioResponse atualizarCardapio(String id, AtualizarDescritivosCardapioRequest atualizarDescritivosCardapioRequest);
}
