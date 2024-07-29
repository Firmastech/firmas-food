package danieldjgomes.larica.app.usecase.cardapioPrato.impl;

import danieldjgomes.larica.app.ports.database.CardapioPratoPersist;
import danieldjgomes.larica.app.usecase.cardapioPrato.AddPratosToCardapioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AddPratosToCardapioUseCaseImpl implements AddPratosToCardapioUseCase {

    private final CardapioPratoPersist cardapioPratoPersist;

    @Override
    public void addPratosToCardapio(String cardapioId, List<String> pratoIds) {
        cardapioPratoPersist.addPratosToCardapio(cardapioId, pratoIds);
    }
}
