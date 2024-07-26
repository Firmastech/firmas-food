package danieldjgomes.larica.app.usecase.cardapio.impl;

import danieldjgomes.larica.app.ports.database.CardapioPersist;
import danieldjgomes.larica.app.usecase.cardapio.DesativarCardapioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DesativarCardapioUseCaseImpl implements DesativarCardapioUseCase {
        private final CardapioPersist cardapioPersist;


    public void desativar(String cardapioId) {
        cardapioPersist.desativarCardapio(cardapioId);


    }
}
