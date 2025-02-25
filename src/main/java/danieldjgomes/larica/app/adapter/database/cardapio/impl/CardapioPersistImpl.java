package danieldjgomes.larica.app.adapter.database.cardapio.impl;

import danieldjgomes.larica.app.adapter.database.cardapio.CardapioRepository;
import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.ports.database.CardapioPersist;
import danieldjgomes.larica.app.usecase.GerarUUIDUseCase;
import danieldjgomes.larica.app.usecase.cardapio.exception.CardapioNotFoundException;
import danieldjgomes.larica.core.categoria.entity.CategoriaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
        cardapio.setAtualizado(dataAtual);
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
    public Optional<CardapioEntity> buscarDetalheCardapio(String cardapioId, String restauranteId) {
      return cardapioRepository.findCardapioByIdAndRestauranteIdAndAtivoTrue(cardapioId, restauranteId);
    }

    @Override
    public void desativarCardapio(String cardapioId, String restauranteId) {
        Optional<CardapioEntity> cardapioEntity = buscarDetalheCardapio(cardapioId, restauranteId);
        if(cardapioEntity.isEmpty()){
            throw new CardapioNotFoundException();
        }
        CardapioEntity cardapioEncontrado = cardapioEntity.get();
        cardapioEncontrado.setAtivo(false);
        cardapioEncontrado.setDeletado(LocalDateTime.now());
        cardapioRepository.save(cardapioEncontrado);
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
