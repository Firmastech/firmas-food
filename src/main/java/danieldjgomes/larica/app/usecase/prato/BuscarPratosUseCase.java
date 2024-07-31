package danieldjgomes.larica.app.usecase.prato;

import danieldjgomes.larica.app.usecase.prato.response.PratoResponse;

import java.util.List;

public interface BuscarPratosUseCase {

    List<PratoResponse> buscarPratoList();

    PratoResponse buscarPrato(String id);
}
