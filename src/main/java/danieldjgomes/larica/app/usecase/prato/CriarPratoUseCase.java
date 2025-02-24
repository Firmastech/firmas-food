package danieldjgomes.larica.app.usecase.prato;

import danieldjgomes.larica.app.usecase.prato.reqeust.CriarPratoRequest;
import danieldjgomes.larica.app.usecase.prato.response.CriarPratoResponse;
import danieldjgomes.larica.app.usecase.prato.response.PratoResponse;

public interface CriarPratoUseCase {

    CriarPratoResponse criar(CriarPratoRequest prato);
}
