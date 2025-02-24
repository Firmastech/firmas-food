package danieldjgomes.larica.app.usecase.categoria;

import danieldjgomes.larica.core.categoria.dtos.CriarCategoriaRequest;
import danieldjgomes.larica.core.categoria.dtos.CriarCategoriaResponse;

public interface CriarCategoriaUseCase {

    CriarCategoriaResponse criar(CriarCategoriaRequest categoria);
}
