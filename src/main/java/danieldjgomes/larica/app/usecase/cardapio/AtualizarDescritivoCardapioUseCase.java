package danieldjgomes.larica.app.usecase.cardapio;

import danieldjgomes.larica.app.usecase.cardapio.request.AtualizarDescritivosCardapioRequest;
import danieldjgomes.larica.app.usecase.cardapio.response.AtualizarCardapioResponse;

import java.util.Optional;

public interface AtualizarDescritivoCardapioUseCase {

    Optional<AtualizarCardapioResponse> atualizarCardapio(String cardapioId, AtualizarDescritivosCardapioRequest atualizarDescritivosCardapioRequest);
}