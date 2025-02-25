package danieldjgomes.larica.app.usecase.cardapio.impl;

import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.ports.database.CardapioPersist;
import danieldjgomes.larica.app.usecase.cardapio.CriarCardapioUseCase;
import danieldjgomes.larica.app.usecase.cardapio.request.CriarCardapioRequest;
import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;
import danieldjgomes.larica.infrastructure.AuthorizationService;
import danieldjgomes.larica.infrastructure.mapper.CardapioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CriarCardapioUseCaseImpl implements CriarCardapioUseCase {
    private final CardapioPersist cardapioPersist;
    private final CardapioMapper cardapioMapper;

    public CardapioResponse criarCardapio(CriarCardapioRequest cardapioRequest) {
        UsuarioEntity usuario = AuthorizationService.findUsuario();
        CardapioEntity cardapio = cardapioMapper.toEntity(cardapioRequest);
        cardapio.setRestaurante(usuario.getRestaurante());
        cardapio = cardapioPersist.criar(cardapio);
        return cardapioMapper.toCardapioResponse(cardapio);
    }

}
