package danieldjgomes.larica.app.usecase.cardapio.impl;


import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.ports.database.CardapioPersist;
import danieldjgomes.larica.app.usecase.cardapio.BuscarTodosCardapioUseCase;
import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;
import danieldjgomes.larica.infrastructure.AuthorizationService;
import danieldjgomes.larica.infrastructure.mapper.CardapioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BuscarCardapiosPertencentesAoRestauranteUseCaseImpl implements BuscarTodosCardapioUseCase {

    private final CardapioPersist cardapioPersist;
    private final CardapioMapper cardapioMapper;

    public List<CardapioResponse> buscarCardapioList() {
        List<CardapioEntity> cardapioResumido = cardapioPersist.buscarCardapios();
        return cardapioResumido
                .stream()
                .map(cardapioMapper::toCardapioResponse)
                .toList();
    }

    public Page<CardapioResponse> buscarTodosCardapios(Pageable pageable) {
        UsuarioEntity usuario = AuthorizationService.findUsuario();
        return cardapioPersist.buscarTodosCardapios(usuario.getRestaurante().getId(),pageable)
                .map(cardapioMapper::toCardapioResponse);
    }

}
