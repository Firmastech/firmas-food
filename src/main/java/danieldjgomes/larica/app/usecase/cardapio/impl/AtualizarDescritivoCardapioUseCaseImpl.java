package danieldjgomes.larica.app.usecase.cardapio.impl;

import danieldjgomes.larica.app.ports.database.CardapioPersist;
import danieldjgomes.larica.app.usecase.cardapio.AtualizarDescritivoCardapioUseCase;
import danieldjgomes.larica.app.usecase.cardapio.BuscarCardapiosPertencentesAoRestauranteUseCase;
import danieldjgomes.larica.app.usecase.cardapio.exception.CardapioNotFoundException;
import danieldjgomes.larica.app.usecase.cardapio.response.AtualizarCardapioResponse;
import danieldjgomes.larica.app.usecase.cardapio.request.AtualizarDescritivosCardapioRequest;
import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;
import danieldjgomes.larica.infrastructure.mapper.CardapioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AtualizarDescritivoCardapioUseCaseImpl implements AtualizarDescritivoCardapioUseCase {

    private final CardapioPersist cardapioPersist;
    private final BuscarCardapiosPertencentesAoRestauranteUseCase buscarCardapiosPertencentesAoRestauranteUseCase;


    public AtualizarCardapioResponse atualizarCardapio(String cardapioId, AtualizarDescritivosCardapioRequest atualizarDescritivosCardapioRequest, String restauranteId ) {

        Integer cardapioAtualizado = cardapioPersist.atualizarDescritivos(cardapioId, atualizarDescritivosCardapioRequest.getNome(), atualizarDescritivosCardapioRequest.getDescricao());
        if (cardapioAtualizado >= 1) {
            CardapioResponse cardapioResponse = buscarCardapiosPertencentesAoRestauranteUseCase.buscarDetalheCardapio(cardapioId, restauranteId);
           return CardapioMapper.INSTANCE.toAtualizarCardapioResponse(cardapioResponse);
        }
        throw new CardapioNotFoundException();
    }

}
