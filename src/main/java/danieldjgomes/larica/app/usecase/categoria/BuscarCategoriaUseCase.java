package danieldjgomes.larica.app.usecase.categoria;

import danieldjgomes.larica.app.usecase.categoria.response.CategoriaResponse;

import java.util.List;

public interface BuscarCategoriaUseCase {

    List<CategoriaResponse> buscarCategorias(String cardapioId);

    CategoriaResponse buscarCategoria(String categoriaId);
}
