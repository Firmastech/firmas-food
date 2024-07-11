package danieldjgomes.larica.core.cardapio.service.impl;

import danieldjgomes.larica.core.cardapio.entity.Cardapio;
import danieldjgomes.larica.core.cardapio.repository.CardapioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CardapioServiceImpl {

    private CardapioRepository cardapioRepository;


    public Cardapio createCardapio(Cardapio cardapio) {
        return cardapioRepository.save(cardapio);
    }

    public Optional<Cardapio> findCardapioById(Long id) {
        return cardapioRepository.findById(id);
    }

    public List<Cardapio> cardapioList() {
        return cardapioRepository.findAll();
    }


}
