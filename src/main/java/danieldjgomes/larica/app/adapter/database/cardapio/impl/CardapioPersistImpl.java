package danieldjgomes.larica.app.adapter.database.cardapio.impl;

import danieldjgomes.larica.app.adapter.database.cardapio.CardapioResumidoRepository;
import danieldjgomes.larica.app.ports.database.CardapioPersist;
import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioResumidoEntity;
import danieldjgomes.larica.app.adapter.database.cardapio.CardapioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CardapioPersistImpl implements CardapioPersist {

    private final CardapioRepository cardapioRepository;
    private final CardapioResumidoRepository cardapioResumidoRepository;

    @Override
    public CardapioEntity criar(CardapioEntity cardapio) {
        Date dataAtual = new Date();
        cardapio.setCriado(dataAtual);
        cardapio.setAtualizado(dataAtual);
        return cardapioRepository.save(cardapio);
    }

    @Override
    public CardapioEntity atualizarDescritivos(String id, String nome, String descricao) {
        return cardapioRepository.atualizarDescritivos(id, nome, descricao);
    }

    @Override
    public List<CardapioResumidoEntity> buscarCardapios(String restauranteId) {
        return cardapioResumidoRepository.findAllByRestauranteIdAndEstaAtivoIsTrue(restauranteId);
    }

    @Override
    public CardapioEntity buscarDetalheCardapio(String cardapioId, String restauranteId) {
        return cardapioRepository.findCardapioByIdAndRestauranteIdAndEstaAtivoIsTrue(cardapioId,restauranteId);
    }

    @Override
    public void desativarCardapio(String cardapioId) {
        cardapioRepository.desativarCardapio(cardapioId, new Date());
    }
}
