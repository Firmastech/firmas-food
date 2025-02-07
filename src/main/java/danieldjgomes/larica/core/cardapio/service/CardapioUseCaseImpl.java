package danieldjgomes.larica.core.cardapio.service;

import danieldjgomes.larica.core.cardapio.dtos.request.CardapioRequestDTO;
import danieldjgomes.larica.core.cardapio.dtos.request.CardapioUpdateRequestDTO;
import danieldjgomes.larica.core.cardapio.dtos.response.CardapioResponseDTO;
import danieldjgomes.larica.core.cardapio.entity.CardapioEntity;
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
        CardapioEntity cardapioEntity = CardapioMapper.INSTANCE.toEntity(cardapioRequestDTO);
        cardapioEntity.setId(UUID.randomUUID().toString());
        cardapioEntity = cardapioRepository.save(cardapioEntity);
        return CardapioMapper.INSTANCE.toDto(cardapioEntity);
    }

    public CardapioResponseDTO atualizarCardapio(String id, CardapioUpdateRequestDTO cardapioUpdateRequestDTO) {
        CardapioEntity cardapioEntity = getExistingCardapio(id);
        CardapioMapper.INSTANCE.updateCardapioFromDto(cardapioUpdateRequestDTO, cardapioEntity);
        cardapioEntity = cardapioRepository.save(cardapioEntity);
        return CardapioMapper.INSTANCE.toDto(cardapioEntity);
    }

    public void desativarCardapio(String id) {
        CardapioEntity cardapioEntity = cardapioRepository
                .findById(id).orElseThrow(() ->
                        new EntityNotFoundException("Cardapio not found with id: " + id));
        cardapioEntity.setEstaAtivo(false);
        cardapioRepository.save(cardapioEntity);
    }

    private CardapioEntity getExistingCardapio(String id) {
        return cardapioRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Cardapio not found with id: " + id));
    }

}
