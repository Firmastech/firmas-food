package danieldjgomes.larica.core.cardapio.service;

import danieldjgomes.larica.core.cardapio.entity.Cardapio;
import danieldjgomes.larica.core.cardapio.exception.CardapioNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CardapioService {

    Cardapio createCardapio(Cardapio cardapio);
    List<Cardapio> listAllCardapios();
    Optional<Cardapio> getCardapioById(Long id);
    Optional<Cardapio> updateCardapio(Long id, Cardapio updatedCardapio) throws CardapioNotFoundException;
    void deleteCardapioById(Long id);
}
