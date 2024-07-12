package danieldjgomes.larica.core.usecases;

import danieldjgomes.larica.core.prato.entity.Prato;

import java.util.List;
import java.util.Optional;

public interface PratoUseCase {

    Prato createPrato(Prato prato);
    List<Prato> listAllPratos();
    Optional<Prato> getPratoById(Long id);
    Optional<Prato> updatePrato(Long id, Prato updatedPrato);
    void deletePrato(Long id);
}
