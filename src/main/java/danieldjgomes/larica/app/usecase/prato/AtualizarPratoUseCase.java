package danieldjgomes.larica.app.usecase.prato;

import danieldjgomes.larica.app.usecase.prato.reqeust.AtualizarPratoRequest;
import danieldjgomes.larica.app.usecase.prato.response.PratoResponse;

import java.util.Optional;

public interface AtualizarPratoUseCase {

    Optional<PratoResponse> updatePrato(String pratoId, AtualizarPratoRequest pratoRequest);
}
