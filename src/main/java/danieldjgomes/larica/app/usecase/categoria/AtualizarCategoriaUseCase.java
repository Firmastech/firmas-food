package danieldjgomes.larica.app.usecase.categoria;

import danieldjgomes.larica.app.usecase.categoria.request.AtualizarCategoriaRequest;
import danieldjgomes.larica.app.usecase.categoria.response.CategoriaResponse;

import java.util.Optional;

public interface AtualizarCategoriaUseCase {

    Optional<CategoriaResponse> updateCategoria(String id, AtualizarCategoriaRequest categoriaRequest);
}
