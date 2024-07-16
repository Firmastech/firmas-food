package danieldjgomes.larica.core.usecases;

import danieldjgomes.larica.core.culinaria.dtos.CulinariaRequestDTO;
import danieldjgomes.larica.core.culinaria.dtos.CulinariaResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CulinariaUseCase {

    CulinariaResponseDTO createCulinaria(CulinariaRequestDTO request);

    Optional<CulinariaResponseDTO> getCulinariaById(UUID id);

    List<CulinariaResponseDTO> listAllCulinarias();

    Optional<CulinariaResponseDTO> updateCulinaria(UUID id, CulinariaRequestDTO request);

    void deleteCulinaria(UUID id);
}
