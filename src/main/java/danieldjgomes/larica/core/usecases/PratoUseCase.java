package danieldjgomes.larica.core.usecases;

import danieldjgomes.larica.core.prato.dtos.PratoRequestDTO;
import danieldjgomes.larica.core.prato.dtos.PratoResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PratoUseCase {

    PratoResponseDTO createPrato(PratoRequestDTO pratoRequest);

    PratoResponseDTO applayDescontoToPrato(UUID pratoId, UUID descontoId);

    Optional<PratoResponseDTO> getPratoById(UUID id);

    List<PratoResponseDTO> listAllPratos();

    Optional<PratoResponseDTO> updatePrato(UUID id, PratoRequestDTO pratoRequest);

    void deletePrato(UUID id);
}
