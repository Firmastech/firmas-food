package danieldjgomes.larica.app.usecase.categoria;

import danieldjgomes.larica.app.usecase.categoria.request.CriarCategoriaRequest;
import danieldjgomes.larica.app.usecase.categoria.response.CategoriaResponse;

public interface CriarCategoriaUseCase {

    CategoriaResponse criar(CriarCategoriaRequest categoria);
}
