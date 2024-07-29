package danieldjgomes.larica.app.adapter.database.cardapioPrato.impl;

import danieldjgomes.larica.app.adapter.database.cardapio.CardapioRepository;
import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.adapter.database.cardapioPrato.model.CardapioPratoEntity;
import danieldjgomes.larica.app.adapter.database.cardapioPrato.repository.CardapioPratoRepository;
import danieldjgomes.larica.app.adapter.database.prato.model.PratoEntity;
import danieldjgomes.larica.app.ports.database.CardapioPratoPersist;
import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;
import danieldjgomes.larica.app.usecase.cardapioPrato.exception.CardapioPratoNotFoundException;
import danieldjgomes.larica.app.usecase.prato.response.PratoResponse;
import danieldjgomes.larica.app.adapter.database.prato.repository.PratoRepository;
import danieldjgomes.larica.infrastructure.mapper.CardapioMapper;
import danieldjgomes.larica.infrastructure.mapper.PratoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CardapioPratoPersistImpl implements CardapioPratoPersist {


    private final CardapioRepository cardapioRepository;
    private final CardapioPratoRepository cardapioPratoRepository;
    private final PratoRepository pratoRepository;

    public void addPratosToCardapio(String cardapioId, List<String> pratoIds) {
        if (!cardapioRepository.existsById(cardapioId)) {
            throw new CardapioPratoNotFoundException();
        }

        List<PratoEntity> pratoEntities = pratoRepository.findAllById(pratoIds)
                .stream()
                .filter(PratoEntity::getEstaAtivo)
                .toList();
        if (pratoEntities.size() != pratoIds.size()) {
            throw new CardapioPratoNotFoundException();
        }

        for (String pratoId : pratoIds) {
            cardapioPratoRepository.save(new CardapioPratoEntity(cardapioId, pratoId));
        }

    }

    public CardapioResponse getCardapioById(String cardapioId) {
        CardapioEntity cardapio = cardapioRepository.findById(cardapioId)
                .filter(CardapioEntity::getEstaAtivo)
                .orElseThrow(CardapioPratoNotFoundException::new);

        List<CardapioPratoEntity> cardapioPratoEntities = cardapioPratoRepository.findByCardapioId(cardapioId);
        List<PratoResponse> pratos = cardapioPratoEntities.stream()
                .map(cp -> pratoRepository.findById(cp.getPratoId())
                        .filter(PratoEntity::getEstaAtivo)
                        .map(PratoMapper.INSTANCE::criarPratoResponse)
                        .orElseThrow(CardapioPratoNotFoundException::new))
                .toList();

        CardapioResponse cardapioResponseDTO = CardapioMapper.INSTANCE.criarCardapiotoResponse(cardapio);
        cardapioResponseDTO.setPratos(pratos);

        return cardapioResponseDTO;
    }

    public void removePratosFromCardapio(String cardapioId, List<String> pratoIds) {
        cardapioPratoRepository.deleteByCardapioIdAndPratoIdIn(cardapioId, pratoIds);
    }
}
