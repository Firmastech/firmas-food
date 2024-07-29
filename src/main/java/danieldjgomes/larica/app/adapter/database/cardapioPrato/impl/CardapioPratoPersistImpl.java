package danieldjgomes.larica.app.adapter.database.cardapioPrato.impl;

import danieldjgomes.larica.app.adapter.database.cardapio.CardapioRepository;
import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.adapter.database.cardapioPrato.model.CardapioPratoEntity;
import danieldjgomes.larica.app.adapter.database.cardapioPrato.repository.CardapioPratoRepository;
import danieldjgomes.larica.app.ports.database.CardapioPratoPersist;
import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;
import danieldjgomes.larica.app.usecase.cardapioPrato.exception.CardapioPratoNotFoundException;
import danieldjgomes.larica.core.prato.dtos.PratoResponseDTO;
import danieldjgomes.larica.core.prato.entity.Prato;
import danieldjgomes.larica.core.prato.repository.PratoRepository;
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

        List<Prato> pratos = pratoRepository.findAllById(pratoIds)
                .stream()
                .filter(Prato::getEstaAtivo)
                .toList();
        if (pratos.size() != pratoIds.size()) {
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
        List<PratoResponseDTO> pratos = cardapioPratoEntities.stream()
                .map(cp -> pratoRepository.findById(cp.getPratoId())
                        .filter(Prato::getEstaAtivo)
                        .map(PratoMapper.INSTANCE::toResponseDTO)
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
