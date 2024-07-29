package danieldjgomes.larica.app.usecase.cardapioPrato.impl;

import danieldjgomes.larica.app.ports.database.CardapioPratoPersist;
import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;
import danieldjgomes.larica.app.usecase.cardapioPrato.BuscarCardapioByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BuscarCardapioByIdUseCaseImpl implements BuscarCardapioByIdUseCase {

    private final CardapioPratoPersist cardapioPratoPersist;

    public CardapioResponse buscarCardapioById(String cardapioId) {
        CardapioResponse cardapio = cardapioPratoPersist.getCardapioById(cardapioId);
        return cardapio;

    }
}
