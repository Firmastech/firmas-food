package danieldjgomes.larica.core.usecases;

import danieldjgomes.larica.core.prato.dtos.PratoRequestDTO;
import danieldjgomes.larica.core.prato.dtos.PratoResponseDTO;

import java.util.List;
import java.util.Optional;

public interface PratoUseCase {

    PratoResponseDTO createPrato(PratoRequestDTO pratoRequest);

    Optional<PratoResponseDTO> findPratoById(String id);

    List<PratoResponseDTO> getAllPratos();

    Optional<PratoResponseDTO> updatePrato(String id, PratoRequestDTO pratoRequest);

    void disablePrato(String id);

}
