package danieldjgomes.larica.app.usecase.cardapio;

import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;

import java.util.Optional;


public interface BuscarDetalheCardapioUseCase {

    Optional<CardapioResponse> buscarDetalheCardapio(String cardapioId);
}
