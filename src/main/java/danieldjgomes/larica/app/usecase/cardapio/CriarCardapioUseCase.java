package danieldjgomes.larica.app.usecase.cardapio;

import danieldjgomes.larica.app.usecase.cardapio.request.CriarCardapioRequest;
import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;

public interface CriarCardapioUseCase {

    CardapioResponse criar(CriarCardapioRequest request);
}
