package danieldjgomes.larica.app.usecase.categoria;

import danieldjgomes.larica.app.usecase.categoria.response.BuscarDetalhesCategoriaResponse;
import danieldjgomes.larica.app.usecase.categoria.response.CategoriaResponse;

import java.util.List;

public interface BuscarCategoriaUseCase {

    List<CategoriaResponse> buscarCategorias(String cardapioId);

    BuscarDetalhesCategoriaResponse buscarDetalhesCategoria(String categoriaId, String cardapiosId);
}
