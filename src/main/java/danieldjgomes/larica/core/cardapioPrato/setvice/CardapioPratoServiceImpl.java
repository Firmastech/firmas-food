package danieldjgomes.larica.core.cardapioPrato.setvice;

import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;
import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.adapter.database.cardapio.CardapioRepository;
import danieldjgomes.larica.core.cardapioPrato.entity.CardapioPrato;
import danieldjgomes.larica.core.cardapioPrato.repository.CardapioPratoRepository;
import danieldjgomes.larica.core.exception.EntityNotFoundException;
import danieldjgomes.larica.core.prato.dtos.PratoResponseDTO;
import danieldjgomes.larica.core.prato.entity.Prato;
import danieldjgomes.larica.core.prato.repository.PratoRepository;
import danieldjgomes.larica.core.usecases.CardapioPratoUseCase;
import danieldjgomes.larica.infrastructure.mapper.CardapioMapper;
import danieldjgomes.larica.infrastructure.mapper.PratoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardapioPratoServiceImpl implements CardapioPratoUseCase {


    private final CardapioRepository cardapioRepository;
    private final PratoRepository pratoRepository;
    private final CardapioPratoRepository cardapioPratoRepository;

    public void addPratosToCardapio(String cardapioId, List<String> pratoIds) {
        if (!cardapioRepository.existsById(cardapioId)) {
            throw new EntityNotFoundException("Cardapio not found with id: " + cardapioId);
        }

        List<Prato> pratos = pratoRepository.findAllById(pratoIds)
                .stream()
                .filter(Prato::getEstaAtivo)
                .toList();
        if (pratos.size() != pratoIds.size()) {
            throw new EntityNotFoundException("List of pratos not found, or is disabled with ids: " + pratoIds);
        }

        for (String pratoId : pratoIds) {
            cardapioPratoRepository.save(new CardapioPrato(cardapioId, pratoId));
        }

    }

    public CardapioResponse getCardapioById(String cardapioId) {
        CardapioEntity cardapio = cardapioRepository.findById(cardapioId)
                .filter(CardapioEntity::getEstaAtivo)
                .orElseThrow(() -> new EntityNotFoundException("Cardápio not found, or is disabled"));

        List<CardapioPrato> cardapioPratos = cardapioPratoRepository.findByCardapioId(cardapioId);
        List<PratoResponseDTO> pratos = cardapioPratos.stream()
                .map(cp -> pratoRepository.findById(cp.getPratoId())
                        .filter(Prato::getEstaAtivo)
                        .map(PratoMapper.INSTANCE::toResponseDTO)
                        .orElseThrow(() -> new EntityNotFoundException("Prato not found, or is disabled with id: " + cp.getPratoId())))
                .toList();

        CardapioResponse cardapioResponseDTO = CardapioMapper.INSTANCE.criarCardapiotoResponse(cardapio);
        cardapioResponseDTO.setPratos(pratos);

        return cardapioResponseDTO;
    }

    public void removePratosFromCardapio(String cardapioId, List<String> pratoIds) {
        cardapioPratoRepository.deleteByCardapioIdAndPratoIdIn(cardapioId, pratoIds);
    }

}

