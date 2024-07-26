package danieldjgomes.larica.app.usecase.cardapio.impl;

import danieldjgomes.larica.app.ports.database.CardapioPersist;
import danieldjgomes.larica.app.usecase.cardapio.AtualizarDescritivoCardapioUseCase;
import danieldjgomes.larica.app.usecase.cardapio.response.AtualizarCardapioResponse;
import danieldjgomes.larica.app.usecase.cardapio.request.AtualizarDescritivosCardapioRequest;
import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.infrastructure.mapper.CardapioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AtualizarDescritivoCardapioUseCaseImpl implements AtualizarDescritivoCardapioUseCase {

    private final CardapioPersist cardapioPersist;


    public AtualizarCardapioResponse atualizarCardapio(String id, AtualizarDescritivosCardapioRequest atualizarDescritivosCardapioRequest) {

        CardapioEntity cardapio = cardapioPersist.atualizarDescritivos(id, atualizarDescritivosCardapioRequest.getNome(), atualizarDescritivosCardapioRequest.getDescricao());
        return CardapioMapper.INSTANCE.atualizarCardapiotoResponse(cardapio);
    }
}
