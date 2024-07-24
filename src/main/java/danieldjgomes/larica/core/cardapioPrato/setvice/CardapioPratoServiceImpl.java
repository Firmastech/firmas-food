package danieldjgomes.larica.core.cardapioPrato.setvice;

import danieldjgomes.larica.core.cardapio.dtos.response.CardapioResponseDTO;
import danieldjgomes.larica.core.cardapio.entity.Cardapio;
import danieldjgomes.larica.core.cardapio.repository.CardapioRepository;
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
import java.util.stream.Collectors;

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

    public CardapioResponseDTO getCardapioById(String cardapioId) {
        Cardapio cardapio = cardapioRepository.findById(cardapioId)
                .filter(Cardapio::getEstaAtivo)
                .orElseThrow(() -> new EntityNotFoundException("Cardápio not found, or is disabled"));

        List<CardapioPrato> cardapioPratos = cardapioPratoRepository.findByCardapioId(cardapioId);
        List<PratoResponseDTO> pratos = cardapioPratos.stream()
                .map(cp -> pratoRepository.findById(cp.getPratoId())
                        .filter(Prato::getEstaAtivo)
                        .map(PratoMapper.INSTANCE::toResponseDTO)
                        .orElseThrow(() -> new EntityNotFoundException("Prato not found, or is disabled with id: " + cp.getPratoId())))
                .toList();

        CardapioResponseDTO cardapioResponseDTO = CardapioMapper.INSTANCE.toDto(cardapio);
        cardapioResponseDTO.setPratos(pratos);

        return cardapioResponseDTO;
    }

    public void removePratosFromCardapio(String cardapioId, List<String> pratoIds) {
        List<CardapioPrato> cardapioPratos = cardapioPratoRepository.findByCardapioIdAndPratoIdIn(cardapioId, pratoIds);
        if (cardapioPratos.isEmpty()) {
            throw new EntityNotFoundException("No matching relationships found between cardapio and pratos");
        }
        cardapioPratoRepository.deleteAll(cardapioPratos);
    }

}

