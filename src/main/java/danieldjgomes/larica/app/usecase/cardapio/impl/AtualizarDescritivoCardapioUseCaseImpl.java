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
        Optional<CardapioEntity> cardapioBuscado = cardapioPersist
                .buscarDetalheCardapio(cardapioId, usuario.getRestaurante().getId());

        CardapioEntity cardapioParaAtualizar = cardapioBuscado
                .map(c -> {
                    c.setDescricao(atualizarDescritivosCardapioRequest.getDescricao());
                    c.setNome(atualizarDescritivosCardapioRequest.getNome());
                    return c;
                })
                .orElseThrow(CardapioNotFoundException::new);

        Optional<CardapioEntity> cardaprioAtualizado = Optional.ofNullable(cardapioPersist.atualizarDescritivos(cardapioParaAtualizar));
        return cardaprioAtualizado.map(cardapioMapper::updateCardapioFromDto);

    }

}
