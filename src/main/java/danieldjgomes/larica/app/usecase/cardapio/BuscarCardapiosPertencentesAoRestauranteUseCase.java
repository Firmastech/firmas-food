package danieldjgomes.larica.app.usecase.cardapio;

import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;


public interface BuscarCardapiosPertencentesAoRestauranteUseCase {

    CardapioResponse buscarDetalheCardapio(String cardapioId);
}
