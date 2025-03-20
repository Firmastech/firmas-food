package danieldjgomes.larica.app.usecase.cardapioPrato;

import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;

public interface BuscarCardapioByIdUseCase {

    CardapioResponse buscarCardapioById(String cardapioId);
}
