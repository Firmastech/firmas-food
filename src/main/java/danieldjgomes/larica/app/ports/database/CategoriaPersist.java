package danieldjgomes.larica.app.ports.database;

import danieldjgomes.larica.app.adapter.database.categoria.model.CategoriaEntity;
import danieldjgomes.larica.app.usecase.categoria.request.AtualizarCategoriaRequest;
import danieldjgomes.larica.app.usecase.categoria.response.CategoriaResponse;

import java.util.List;
import java.util.Optional;

public interface CategoriaPersist {

    CategoriaEntity createCategoria(CategoriaEntity categoria);

    Optional<CategoriaEntity> getCategoriaById(String id);

    List<CategoriaEntity> listAllCategorias();

    Optional<CategoriaEntity> updateCategoria(String id, AtualizarCategoriaRequest categoriaRequest);

    void disableCategoria(String id);
}
