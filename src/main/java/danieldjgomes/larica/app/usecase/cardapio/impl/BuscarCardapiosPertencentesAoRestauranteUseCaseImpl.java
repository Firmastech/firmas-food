package danieldjgomes.larica.app.usecase.cardapio.impl;

import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioResumidoEntity;
import danieldjgomes.larica.app.ports.database.CardapioPersist;
import danieldjgomes.larica.app.usecase.cardapio.BuscarCardapiosPertencentesAoRestauranteUseCase;
import danieldjgomes.larica.app.usecase.cardapio.exception.CardapioNotFoundException;
import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;
import danieldjgomes.larica.app.usecase.cardapio.response.ResumoCardapioResponse;
import danieldjgomes.larica.infrastructure.mapper.CardapioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BuscarCardapiosPertencentesAoRestauranteUseCaseImpl implements BuscarCardapiosPertencentesAoRestauranteUseCase {

    private final CardapioPersist cardapioPersist;

    public List<ResumoCardapioResponse> buscarCardapio(String restauranteId) {
        List<CardapioResumidoEntity> cardapioResumido = cardapioPersist.buscarCardapios(restauranteId);
        return cardapioResumido.stream().map(CardapioMapper.INSTANCE::toCardapioResumo).toList();
    }

    public CardapioResponse buscarDetalheCardapio(String cardapioId, String restauranteId) {
        Optional<CardapioEntity> cardapio = cardapioPersist.buscarDetalheCardapio(cardapioId, restauranteId);
        if (cardapio.isPresent()) {
            return CardapioMapper.INSTANCE.toResponse(cardapio.get());
        }
        throw new CardapioNotFoundException();
    }
}
