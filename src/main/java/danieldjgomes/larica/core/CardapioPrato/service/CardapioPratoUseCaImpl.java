package danieldjgomes.larica.core.CardapioPrato.service;

import danieldjgomes.larica.core.CardapioPrato.entity.CardapioPrato;
import danieldjgomes.larica.core.CardapioPrato.repository.CardapioPratoRepository;
import danieldjgomes.larica.core.cardapio.dtos.request.AddPratosToCardapioRequest;
import danieldjgomes.larica.core.cardapio.dtos.response.CardapioResponseDTO;
import danieldjgomes.larica.core.cardapio.entity.Cardapio;
import danieldjgomes.larica.core.cardapio.repository.CardapioRepository;
import danieldjgomes.larica.core.exception.EntityNotFoundException;
import danieldjgomes.larica.core.prato.entity.Prato;
import danieldjgomes.larica.core.prato.repository.PratoRepository;
import danieldjgomes.larica.core.usecases.CardapioPratoUseCase;
import danieldjgomes.larica.infrastructure.mapper.CardapioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CardapioPratoUseCaImpl implements CardapioPratoUseCase {

    private final CardapioRepository cardapioRepository;
    private final CardapioPratoRepository cardapioPratoRepository;
    private final PratoRepository pratoRepository;

    public CardapioResponseDTO adicionarPratos(String cardapioId, AddPratosToCardapioRequest request) {

        Cardapio cardapio = cardapioRepository.findById(request.getCardapioId())
                .orElseThrow(() -> new EntityNotFoundException("Cardápio não encontrado"));

        List<Prato> pratos = findAllPratosById(request.getPratoIds());

        if (pratos.size() != request.getPratoIds().size()) {
            throw new EntityNotFoundException("Um ou mais pratos não foram encontrados.");
        }

        List<CardapioPrato> cardapioPratos = pratos.stream()
                .map(prato -> new CardapioPrato(cardapio, prato))
                .collect(Collectors.toList());

        cardapioPratoRepository.saveAll(cardapioPratos);

        cardapio.setAtualizado(LocalDateTime.now());
        cardapioRepository.save(cardapio);

        return CardapioMapper.INSTANCE.toDto(cardapio);
    }

    public void removerPrato(String cardapioId, String pratoId) {
        Cardapio cardapio = getExistingCardapio(cardapioId);
        Prato prato = pratoRepository.findById(pratoId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Prato not found with id: " + pratoId));

        CardapioPrato cardapioPrato = cardapioPratoRepository.findByCardapioAndPrato(cardapio, prato)
                .orElseThrow(() -> new EntityNotFoundException("Prato não associado a este cardápio"));

        cardapioPratoRepository.delete(cardapioPrato);
        cardapio.setAtualizado(LocalDateTime.now());

        cardapioRepository.save(cardapio);
    }

    private List<Prato> findAllPratosById(List<String> ids) {
        List<Prato> pratos = pratoRepository.findAllById(ids);
        if (pratos.size() != ids.size()) {
            List<String> foundIds = pratos.stream()
                    .map(Prato::getId)
                    .toList();
            List<String> notFoundIds = ids.stream()
                    .filter(id -> !foundIds.contains(id))
                    .toList();
            throw new EntityNotFoundException("Pratos not found with ids: " + notFoundIds);
        }
        return pratos;
    }

    private Cardapio getExistingCardapio(String id) {
        return cardapioRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Cardapio not found with id: " + id));
    }

}
