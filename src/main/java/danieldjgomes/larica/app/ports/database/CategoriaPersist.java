package danieldjgomes.larica.app.ports.database;

import danieldjgomes.larica.app.adapter.database.categoria.model.CategoriaEntity;

import java.util.List;
import java.util.Optional;

public interface CategoriaPersist {

    CategoriaEntity criarCategoria(CategoriaEntity categoria);

    Optional<CategoriaEntity> buscarDetalhesCategoria(String id);

    List<CategoriaEntity> buscarCategorias(String cardapioId);

    Optional<CategoriaEntity> updateCategoria(CategoriaEntity categoria);

    void disableCategoria(String id);
}
