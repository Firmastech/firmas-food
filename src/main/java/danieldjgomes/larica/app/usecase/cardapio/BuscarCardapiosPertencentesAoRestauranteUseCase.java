package danieldjgomes.larica.app.usecase.cardapio;

import danieldjgomes.larica.app.usecase.cardapio.response.ResumoCardapioResponse;
import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;

import java.util.List;

public interface BuscarCardapiosPertencentesAoRestauranteUseCase {
    List<ResumoCardapioResponse> buscarCardapio(String restauranteId);

    CardapioResponse buscarDetalheCardapio(String cardapioId, String restauranteId);
}
