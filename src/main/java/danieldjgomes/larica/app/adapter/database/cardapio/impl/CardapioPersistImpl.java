package danieldjgomes.larica.app.adapter.database.cardapio.impl;

import danieldjgomes.larica.app.adapter.database.cardapio.CardapioRepository;
import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.ports.database.CardapioPersist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CardapioPersistImpl implements CardapioPersist {

    private final CardapioRepository cardapioRepository;

    @Override
    public CardapioEntity criar(CardapioEntity cardapio) {
        LocalDateTime dataAtual = LocalDateTime.now();
        cardapio.setCriado(dataAtual);
        cardapio.setAtualizado(dataAtual);
        return cardapioRepository.save(cardapio);
    }

    @Override
    public CardapioEntity atualizarDescritivos(CardapioEntity cardapio) {
        return cardapioRepository.save(cardapio);
    }

    @Override
    public List<CardapioEntity> buscarCardapios() {
        return cardapioRepository.findAll();
    }

    @Override
    public Optional<CardapioEntity> buscarDetalheCardapio(String cardapioId) {
        return cardapioRepository.findCardapioByIdAndAtivoTrue(cardapioId);
    }

    @Override
    public void desativarCardapio(String cardapioId) {
        cardapioRepository.desativarCardapio(cardapioId, new Date());
    }

}
