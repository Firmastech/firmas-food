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

import java.time.LocalDateTime;
import java.util.List;
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

    public List<CardapioResponseDTO> getAllCardapios() {
        List<Cardapio> listCardapio = cardapioRepository.findAll()
                .stream()
                .filter(Cardapio::getEstaAtivo)
                .toList();
        return CardapioMapper.INSTANCE.listToDto(listCardapio);
    }

    public CardapioResponseDTO atualizarCardapio(String id, CardapioUpdateRequestDTO cardapioUpdateRequestDTO) {
        Cardapio cardapio = getExistingCardapio(id);
        CardapioMapper.INSTANCE.updateCardapioFromDto(cardapioUpdateRequestDTO, cardapio);
        cardapio = cardapioRepository.save(cardapio);
        return CardapioMapper.INSTANCE.toDto(cardapio);
    }

    public void desativarCardapio(String id) {
        Cardapio cardapio = getExistingCardapio(id);
        cardapio.setAtualizado(LocalDateTime.now());
        cardapio.setDeletado(LocalDateTime.now());
        cardapio.setEstaAtivo(false);
        cardapioRepository.save(cardapio);
    }

    private Cardapio getExistingCardapio(String id) {
        return cardapioRepository.findById(id)
                .stream()
                .filter(Cardapio::getEstaAtivo)
                .findFirst()
                .orElseThrow(() ->
                        new EntityNotFoundException("Cardapio not found, or is disabled with id: " + id));
    }


}
