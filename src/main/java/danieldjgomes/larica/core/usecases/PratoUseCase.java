package danieldjgomes.larica.core.usecases;

import danieldjgomes.larica.core.prato.entity.Prato;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PratoUseCase {

    Prato createPrato(Prato prato);
    List<Prato> listAllPratos();
    Optional<Prato> getPratoById(UUID id);
    Optional<Prato> updatePrato(UUID id, Prato updatedPrato);
    void deletePrato(UUID id);
}
