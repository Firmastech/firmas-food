package danieldjgomes.larica.app.ports.database;


import danieldjgomes.larica.app.adapter.database.prato.model.PratoEntity;
import danieldjgomes.larica.app.usecase.prato.reqeust.AtualizarPratoRequest;

import java.util.List;
import java.util.Optional;

public interface PratoPersist {

    PratoEntity criarPrato(PratoEntity prato);

    Optional<PratoEntity> findPratoById(String id);

    List<PratoEntity> buscarPratosPorRestaurante(String restauranteId);

    Integer updatePrato(String id, AtualizarPratoRequest pratoRequest);

    void desativarPrato(PratoEntity prato);

}
