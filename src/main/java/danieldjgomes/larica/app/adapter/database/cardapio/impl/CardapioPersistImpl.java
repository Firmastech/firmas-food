package danieldjgomes.larica.app.adapter.database.cardapio.impl;

import danieldjgomes.larica.app.adapter.database.cardapio.CardapioRepository;
import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.ports.database.CardapioPersist;
import danieldjgomes.larica.app.usecase.GerarUUIDUseCase;
import danieldjgomes.larica.app.usecase.cardapio.exception.CardapioNotFoundException;
import danieldjgomes.larica.app.adapter.database.categoria.model.CategoriaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CardapioPersistImpl implements CardapioPersist {

    private final CardapioRepository cardapioRepository;
    private final GerarUUIDUseCase gerarUUIDUseCase;

    @Override
    public CardapioEntity criar(CardapioEntity cardapio) {
        LocalDateTime dataAtual = LocalDateTime.now();
        cardapio.setId(gerarUUIDUseCase.gerar());
        cardapio.setCriado(dataAtual);
        return cardapioRepository.save(cardapio);
    }

    @Override
    public CardapioEntity atualizarDescritivos(CardapioEntity cardapio) {
        cardapio.setAtualizado(LocalDateTime.now());
        return cardapioRepository.save(cardapio);
    }

    @Override
    public List<CardapioEntity> buscarCardapios() {
        return cardapioRepository.findAll();
    }

    @Override
    public Page<CardapioEntity> buscarTodosCardapios(String restauranteId, Pageable pageable) {
        return cardapioRepository.findAllByRestauranteIdAndAtivoTrue(restauranteId,pageable);

    }

    @Override
    public Optional<CardapioEntity> buscarCardapioPorId(String restauranteId, String cardapioId) {
        return cardapioRepository.findByIdAndRestauranteIdAndDeletadoNotNull(cardapioId, restauranteId);
    }


    @Override
    public void desabilitarCardapio(CardapioEntity cardapioEntity) {
        cardapioEntity.setAtivo(false);
        cardapioEntity.setDeletado(LocalDateTime.now());
        cardapioRepository.save(cardapioEntity);
    }

    @Override
    public Optional<CardapioEntity> buscarCardapioAtivo(String restauranteId) {
        return cardapioRepository.findFirstByRestauranteIdAndAtivoTrue(restauranteId);
    }

    @Override
    public void adicionarCategorias(CardapioEntity cardapioEntity, List<CategoriaEntity> categorias) {
        cardapioEntity.getCategorias().addAll(categorias);
        cardapioRepository.save(cardapioEntity);
    }

}
