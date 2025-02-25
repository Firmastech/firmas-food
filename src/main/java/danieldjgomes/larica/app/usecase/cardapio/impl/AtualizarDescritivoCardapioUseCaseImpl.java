package danieldjgomes.larica.app.usecase.cardapio.impl;

import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.ports.database.CardapioPersist;
import danieldjgomes.larica.app.usecase.cardapio.AtualizarDescritivoCardapioUseCase;
import danieldjgomes.larica.app.usecase.cardapio.response.AtualizarCardapioResponse;
import danieldjgomes.larica.app.usecase.cardapio.request.AtualizarDescritivosCardapioRequest;
import danieldjgomes.larica.app.usecase.cardapio.exception.CardapioNotFoundException;
import danieldjgomes.larica.infrastructure.AuthorizationService;
import danieldjgomes.larica.infrastructure.mapper.CardapioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AtualizarDescritivoCardapioUseCaseImpl implements AtualizarDescritivoCardapioUseCase {

    private final CardapioPersist cardapioPersist;
    private final CardapioMapper cardapioMapper;

    public Optional<AtualizarCardapioResponse> atualizarCardapio(String cardapioId, AtualizarDescritivosCardapioRequest atualizarDescritivosCardapioRequest) {
        UsuarioEntity usuario = AuthorizationService.findUsuario();
        Optional<CardapioEntity> findByCardapio = cardapioPersist.buscarDetalheCardapio(cardapioId,usuario.getRestaurante().getId());
           if (findByCardapio.isEmpty()){
               throw new CardapioNotFoundException();
           }

        Optional<CardapioEntity> cardaprioAtualizado = Optional.ofNullable(cardapioPersist.atualizarDescritivos(findByCardapio.get()));
        return cardaprioAtualizado.map(cardapioMapper::updateCardapioFromDto);

    }

}
