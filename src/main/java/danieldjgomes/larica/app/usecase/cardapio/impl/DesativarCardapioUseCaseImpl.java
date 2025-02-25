package danieldjgomes.larica.app.usecase.cardapio.impl;

import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.ports.database.CardapioPersist;
import danieldjgomes.larica.app.usecase.cardapio.DesativarCardapioUseCase;
import danieldjgomes.larica.infrastructure.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DesativarCardapioUseCaseImpl implements DesativarCardapioUseCase {
        private final CardapioPersist cardapioPersist;


    public void desativar(String cardapioId) {
        UsuarioEntity usuario = AuthorizationService.findUsuario();
        cardapioPersist.desativarCardapio(cardapioId, usuario.getRestaurante().getId());

    }
}
