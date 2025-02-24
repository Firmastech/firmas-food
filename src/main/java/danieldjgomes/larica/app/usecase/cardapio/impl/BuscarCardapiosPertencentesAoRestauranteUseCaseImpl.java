package danieldjgomes.larica.app.usecase.cardapio.impl;


import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.ports.database.CardapioPersist;
import danieldjgomes.larica.app.usecase.cardapio.BuscarCardapiosPertencentesAoRestauranteUseCase;
import danieldjgomes.larica.app.usecase.cardapio.exception.CardapioNotFoundException;
import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;
import danieldjgomes.larica.infrastructure.mapper.CardapioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BuscarCardapiosPertencentesAoRestauranteUseCaseImpl implements BuscarCardapiosPertencentesAoRestauranteUseCase {

    private final CardapioPersist cardapioPersist;
    private final CardapioMapper cardapioMapper;

    public List<CardapioResponse> buscarCardapioList() {
        List<CardapioEntity> cardapioResumido = cardapioPersist.buscarCardapios();
        return cardapioResumido
                .stream()
                .map(cardapioMapper::toDto)
                .toList();
    }

    public CardapioResponse buscarDetalheCardapio(String cardapioId) {
        Optional<CardapioEntity> cardapio = cardapioPersist.buscarDetalheCardapio(cardapioId);
        if (cardapio.isPresent()) {
            return CardapioMapper.INSTANCE.toDto(cardapio.get());
        }
        throw new CardapioNotFoundException();
    }
}
