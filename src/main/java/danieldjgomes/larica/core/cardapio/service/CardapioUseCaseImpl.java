package danieldjgomes.larica.core.cardapio.service;

import danieldjgomes.larica.core.cardapio.dtos.CardapioRequestDTO;
import danieldjgomes.larica.core.cardapio.dtos.CardapioResponseDTO;
import danieldjgomes.larica.core.cardapio.entity.Cardapio;
import danieldjgomes.larica.core.cardapio.repository.CardapioRepository;
import danieldjgomes.larica.core.culinaria.entity.Culinaria;
import danieldjgomes.larica.core.culinaria.repository.CulinariaRepository;
import danieldjgomes.larica.core.exception.EntityNotFoundException;
import danieldjgomes.larica.core.prato.entity.Prato;
import danieldjgomes.larica.core.prato.repository.PratoRepository;
import danieldjgomes.larica.core.usecases.CardapioUseCase;
import danieldjgomes.larica.infrastructure.mapper.CardapioMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardapioUseCaseImpl implements CardapioUseCase {


    private final CardapioRepository cardapioRepository;
    private final PratoRepository pratoRepository;
    private final CulinariaRepository culinariaRepository;

    public CardapioResponseDTO createCardapio(UUID pratoId, UUID tipoCulinariaId) {

        Prato prato = findPratoById(pratoId);
        Culinaria culinaria = findCulinariaById(tipoCulinariaId);
        Cardapio cardapio = new Cardapio();
        cardapio.setPratoId(prato);
        cardapio.setTipoCulinariaId(culinaria);

        Cardapio savedCardapio = cardapioRepository.save(cardapio);

        return CardapioMapper.INSTANCE.toDto(savedCardapio);
    }


    public Optional<CardapioResponseDTO> getCardapioById(UUID id) {
        return cardapioRepository.findById(id)
                .map(CardapioMapper.INSTANCE::toDto);
    }

    public List<CardapioResponseDTO> listAllCardapios() {
        return cardapioRepository.findAll()
                .stream()
                .map(CardapioMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<CardapioResponseDTO> updateCardapio(UUID id, CardapioRequestDTO cardapioRequest) {
        Cardapio existingCardapio = getExistingCardapio(id);
        Cardapio updatedCardapio = CardapioMapper.INSTANCE.toEntity(cardapioRequest);
        updatedCardapio.setCardapioId(existingCardapio.getCardapioId());
        Prato prato = findPratoById(cardapioRequest.getPratoId().getId());
        Culinaria culinaria = findCulinariaById(cardapioRequest.getTipoCulinariaId().getCulinariaId());
        updatedCardapio.setPratoId(prato);
        updatedCardapio.setTipoCulinariaId(culinaria);
        cardapioRepository.save(updatedCardapio);
        return Optional.of(CardapioMapper.INSTANCE.toDto(updatedCardapio));
    }

    public void deleteCardapio(UUID id) {
        Cardapio existingCardapio = getExistingCardapio(id);
        cardapioRepository.delete(existingCardapio);
    }

    private Cardapio getExistingCardapio(UUID id) {
        return cardapioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cardapio not found with id: " + id));
    }

    private Prato findPratoById(UUID id) {
        return pratoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prato not found with id: " + id));
    }

    private Culinaria findCulinariaById(UUID id) {
        return culinariaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Culinaria not found with id: " + id));
    }

}
