package danieldjgomes.larica.app.usecase.cardapio.impl;

import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.ports.database.CardapioPersist;
import danieldjgomes.larica.app.usecase.GerarUUIDUseCase;
import danieldjgomes.larica.app.usecase.cardapio.CriarCardapioUseCase;
import danieldjgomes.larica.app.usecase.cardapio.request.CriarCardapioRequest;
import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;
import danieldjgomes.larica.infrastructure.mapper.CardapioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CriarCardapioUseCaseImpl implements CriarCardapioUseCase {
    private final CardapioPersist cardapioPersist;
    private final GerarUUIDUseCase gerarUUIDUseCase;

    public CardapioResponse criar(CriarCardapioRequest cardapioRequestDTO) {
        CardapioEntity cardapio = CardapioMapper.INSTANCE.toEntity(cardapioRequestDTO);
        cardapio.setId(gerarUUIDUseCase.gerar());
        cardapio = cardapioPersist.criar(cardapio);
        return CardapioMapper.INSTANCE.criarCardapiotoResponse(cardapio);
    }

}
