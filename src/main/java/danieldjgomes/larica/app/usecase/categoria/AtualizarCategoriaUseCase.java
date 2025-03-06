package danieldjgomes.larica.app.usecase.categoria;

import danieldjgomes.larica.app.usecase.categoria.request.AtualizarCategoriaRequest;
import danieldjgomes.larica.app.usecase.categoria.response.AtualizarCategoriaResponse;

import java.util.Optional;

public interface AtualizarCategoriaUseCase {

    Optional<AtualizarCategoriaResponse> updateCategoria(String id, String cardapioId, AtualizarCategoriaRequest categoriaRequest);
}
