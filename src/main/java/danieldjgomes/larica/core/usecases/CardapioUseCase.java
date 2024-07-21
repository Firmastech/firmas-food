package danieldjgomes.larica.core.usecases;

import danieldjgomes.larica.core.cardapio.dtos.CardapioRequestDTO;
import danieldjgomes.larica.core.cardapio.dtos.CardapioResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CardapioUseCase {

    CardapioResponseDTO createCardapio(UUID pratoId, UUID tipoCulinariaId);

    List<CardapioResponseDTO> listAllCardapios();

    Optional<CardapioResponseDTO> getCardapioById(UUID id);

    Optional<CardapioResponseDTO> updateCardapio(UUID id, CardapioRequestDTO cardapioRequest);

    void deleteCardapio(UUID id);
}
