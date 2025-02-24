package danieldjgomes.larica.app.usecase.categoria;

import danieldjgomes.larica.app.usecase.categoria.request.AtualizarCategoriaRequest;
import danieldjgomes.larica.core.categoria.dtos.AtualizarCategoriaResponse;

import java.util.Optional;

public interface AtualizarCategoriaUseCase {

    Optional<AtualizarCategoriaResponse> updateCategoria(String id, AtualizarCategoriaRequest categoriaRequest);
}
