package danieldjgomes.larica.app.usecase.cardapio.impl;

import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.ports.database.CardapioPersist;
import danieldjgomes.larica.app.usecase.cardapio.BuscarCardapioAtivoUseCase;
import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;
import danieldjgomes.larica.infrastructure.AuthorizationService;
import danieldjgomes.larica.infrastructure.mapper.CardapioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BuscarCardapioAtivoUseCaseImpl implements BuscarCardapioAtivoUseCase {

    private final CardapioPersist cardapioPersist;
    private final CardapioMapper cardapioMapper;
    @Override
    public Optional<CardapioResponse> buscar() {
        UsuarioEntity usuario = AuthorizationService.findUsuario();
        return cardapioPersist.buscarCardapioAtivo(usuario.getRestaurante().getId())
                .map(cardapioMapper::toCardapioResponse);

    }
}
