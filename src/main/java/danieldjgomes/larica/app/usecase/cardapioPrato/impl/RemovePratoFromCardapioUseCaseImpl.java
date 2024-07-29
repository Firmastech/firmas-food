package danieldjgomes.larica.app.usecase.cardapioPrato.impl;

import danieldjgomes.larica.app.ports.database.CardapioPratoPersist;
import danieldjgomes.larica.app.usecase.cardapioPrato.RemovePratoFromCardapioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class RemovePratoFromCardapioUseCaseImpl implements RemovePratoFromCardapioUseCase {

    private final CardapioPratoPersist cardapioPratoPersist;

    public void desativar(String cardapioId, List<String> pratoIds) {
        cardapioPratoPersist.removePratosFromCardapio(cardapioId, pratoIds);
    }
}
