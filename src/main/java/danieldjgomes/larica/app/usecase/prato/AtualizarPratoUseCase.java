package danieldjgomes.larica.app.usecase.prato;

import danieldjgomes.larica.app.usecase.prato.reqeust.AtualizarPratoRequest;
import danieldjgomes.larica.app.usecase.prato.response.AtualizarPratoResponse;

public interface AtualizarPratoUseCase {

    AtualizarPratoResponse updatePrato(String pratoId, AtualizarPratoRequest pratoRequest);
}
