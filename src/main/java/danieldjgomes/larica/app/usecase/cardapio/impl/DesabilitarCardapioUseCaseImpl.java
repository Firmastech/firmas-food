package danieldjgomes.larica.app.usecase.cardapio.impl;

import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.ports.database.CardapioPersist;
import danieldjgomes.larica.app.usecase.cardapio.DesabilitarCardapioUseCase;
import danieldjgomes.larica.app.usecase.cardapio.exception.CardapioNotFoundException;
import danieldjgomes.larica.infrastructure.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DesabilitarCardapioUseCaseImpl implements DesabilitarCardapioUseCase {
        private final CardapioPersist cardapioPersist;


    public void desabilitar(String cardapioId) {
        UsuarioEntity usuario = AuthorizationService.findUsuario();
        Optional<CardapioEntity> cardapioEntity = cardapioPersist.buscarCardapioPorId(usuario.getRestaurante().getId(),cardapioId);
        if(cardapioEntity.isEmpty()){
            throw new CardapioNotFoundException();
        }
        CardapioEntity cardapioEncontrado = cardapioEntity.get();

        cardapioPersist.desabilitarCardapio(cardapioEncontrado);

    }
}
