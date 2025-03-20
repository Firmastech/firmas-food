package danieldjgomes.larica.app.usecase.prato;

import danieldjgomes.larica.app.usecase.prato.response.PratoResponse;

import java.util.List;

public interface BuscarPratosUseCase {

    List<PratoResponse> buscarPratosPorRestauranteId(String id);

    PratoResponse buscarPratoPorId(String id);
}
