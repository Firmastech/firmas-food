package danieldjgomes.larica.core.cardapio.service;

import danieldjgomes.larica.core.cardapio.dtos.request.CardapioRequestDTO;
import danieldjgomes.larica.core.cardapio.dtos.request.CardapioUpdateRequestDTO;
import danieldjgomes.larica.core.cardapio.dtos.response.CardapioResponseDTO;
import danieldjgomes.larica.core.cardapio.entity.Cardapio;
import danieldjgomes.larica.core.cardapio.repository.CardapioRepository;
import danieldjgomes.larica.core.exception.EntityNotFoundException;
import danieldjgomes.larica.core.usecases.CardapioUseCase;
import danieldjgomes.larica.infrastructure.mapper.CardapioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardapioUseCaseImpl implements CardapioUseCase {


    private final CardapioRepository cardapioRepository;

    public CardapioResponseDTO criarCardapio(CardapioRequestDTO cardapioRequestDTO) {
        Cardapio cardapio = CardapioMapper.INSTANCE.toEntity(cardapioRequestDTO);
        cardapio.setId(UUID.randomUUID().toString());
        cardapio = cardapioRepository.save(cardapio);
        return CardapioMapper.INSTANCE.toDto(cardapio);
    }

    public CardapioResponseDTO atualizarCardapio(String id, CardapioUpdateRequestDTO cardapioUpdateRequestDTO) {
        Cardapio cardapio = getExistingCardapio(id);
        CardapioMapper.INSTANCE.updateCardapioFromDto(cardapioUpdateRequestDTO, cardapio);
        cardapio = cardapioRepository.save(cardapio);
        return CardapioMapper.INSTANCE.toDto(cardapio);
    }

    public void desativarCardapio(String id) {
        Cardapio cardapio = cardapioRepository
                .findById(id).orElseThrow(() ->
                        new EntityNotFoundException("Cardapio not found with id: " + id));
        cardapio.setEstaAtivo(false);
        cardapioRepository.save(cardapio);
    }

    private Cardapio getExistingCardapio(String id) {
        return cardapioRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Cardapio not found with id: " + id));
    }

}
