package danieldjgomes.larica.core.usecases;

import danieldjgomes.larica.core.cardapio.entity.Cardapio;
import danieldjgomes.larica.core.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CardapioUseCase {

    Cardapio createCardapio(Cardapio cardapio);
    List<Cardapio> listAllCardapios();
    Optional<Cardapio> getCardapioById(UUID id);
    Optional<Cardapio> updateCardapio(UUID id, Cardapio updatedCardapio) throws EntityNotFoundException;
    void deleteCardapio(UUID id);
}
