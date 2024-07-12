package danieldjgomes.larica.core.usecases;

import danieldjgomes.larica.core.cardapio.entity.Cardapio;
import danieldjgomes.larica.core.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CardapioUseCase {

    Cardapio createCardapio(Cardapio cardapio);
    List<Cardapio> listAllCardapios();
    Optional<Cardapio> getCardapioById(Long id);
    Optional<Cardapio> updateCardapio(Long id, Cardapio updatedCardapio) throws EntityNotFoundException;
    void deleteCardapio(Long id);
}
