//package danieldjgomes.larica.core.CardapioPrato.service;
//
//import danieldjgomes.larica.core.CardapioPrato.repository.CardapioPratoRepository;
//import danieldjgomes.larica.core.cardapio.dtos.request.AddPratosToCardapioRequest;
//import danieldjgomes.larica.core.cardapio.dtos.response.CardapioResponseDTO;
//import danieldjgomes.larica.core.cardapio.entity.CardapioEntity;
//import danieldjgomes.larica.core.cardapio.repository.CardapioRepository;
//import danieldjgomes.larica.core.exception.EntityNotFoundException;
//import danieldjgomes.larica.core.prato.entity.PratoEntity;
//import danieldjgomes.larica.core.prato.repository.PratoRepository;
//import danieldjgomes.larica.core.usecases.CardapioPratoUseCase;
//import danieldjgomes.larica.infrastructure.mapper.CardapioMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//@RequiredArgsConstructor
//public class CardapioPratoUseCaImpl implements CardapioPratoUseCase {
//
//    private final CardapioRepository cardapioRepository;
//    private final CardapioPratoRepository cardapioPratoRepository;
//    private final PratoRepository pratoRepository;
//
//    public CardapioResponseDTO adicionarPratos(String cardapioId, AddPratosToCardapioRequest request) {
//
//        CardapioEntity cardapioEntity = cardapioRepository.findById(request.getCardapioId())
//                .orElseThrow(() -> new EntityNotFoundException("Cardápio não encontrado"));
//
//        List<PratoEntity> pratoEntities = findAllPratosById(request.getPratoIds());
//
//        if (pratoEntities.size() != request.getPratoIds().size()) {
//            throw new EntityNotFoundException("Um ou mais pratos não foram encontrados.");
//        }
//
//        List<CardapioPrato> cardapioPratos = pratoEntities.stream()
//                .map(prato -> new CardapioPrato(cardapioEntity, prato))
//                .collect(Collectors.toList());
//
//        cardapioPratoRepository.saveAll(cardapioPratos);
//
//        cardapioEntity.setAtualizado(LocalDateTime.now());
//        cardapioRepository.save(cardapioEntity);
//
//        return CardapioMapper.INSTANCE.toDto(cardapioEntity);
//    }
//
//    public void removerPrato(String cardapioId, String pratoId) {
//        CardapioEntity cardapioEntity = getExistingCardapio(cardapioId);
//        PratoEntity pratoEntity = pratoRepository.findById(pratoId)
//                .orElseThrow(() ->
//                        new EntityNotFoundException("Prato not found with id: " + pratoId));
//
//        CardapioPrato cardapioPrato = cardapioPratoRepository.findByCardapioAndPrato(cardapioEntity, pratoEntity)
//                .orElseThrow(() -> new EntityNotFoundException("Prato não associado a este cardápio"));
//
//        cardapioPratoRepository.delete(cardapioPrato);
//        cardapioEntity.setAtualizado(LocalDateTime.now());
//
//        cardapioRepository.save(cardapioEntity);
//    }
//
//    private List<PratoEntity> findAllPratosById(List<String> ids) {
//        List<PratoEntity> pratoEntities = pratoRepository.findAllById(ids);
//        if (pratoEntities.size() != ids.size()) {
//            List<String> foundIds = pratoEntities.stream()
//                    .map(PratoEntity::getId)
//                    .toList();
//            List<String> notFoundIds = ids.stream()
//                    .filter(id -> !foundIds.contains(id))
//                    .toList();
//            throw new EntityNotFoundException("Pratos not found with ids: " + notFoundIds);
//        }
//        return pratoEntities;
//    }
//
//    private CardapioEntity getExistingCardapio(String id) {
//        return cardapioRepository.findById(id)
//                .orElseThrow(() ->
//                        new EntityNotFoundException("Cardapio not found with id: " + id));
//    }
//
//}
