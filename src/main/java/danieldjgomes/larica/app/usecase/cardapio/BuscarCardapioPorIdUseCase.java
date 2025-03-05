package danieldjgomes.larica.app.usecase.cardapio;

import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;

import java.util.Optional;

public interface BuscarCardapioPorIdUseCase {

    Optional<CardapioResponse> buscar(String cardapioId);
}
