package danieldjgomes.larica.app.ports.database;

import danieldjgomes.larica.app.adapter.database.prato.model.PratoEntity;
import danieldjgomes.larica.app.usecase.prato.reqeust.AtualizarPratoRequest;

import java.util.List;
import java.util.Optional;

public interface PratoPersist {

    PratoEntity createPrato(PratoEntity prato);

    Optional<PratoEntity> findPratoById(String id);

    List<PratoEntity> getAllPratos();

    Optional<PratoEntity> updatePrato(String id, AtualizarPratoRequest pratoRequest);

    void disablePrato(String id);

}
