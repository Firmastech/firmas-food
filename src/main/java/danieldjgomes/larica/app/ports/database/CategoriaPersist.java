package danieldjgomes.larica.app.ports.database;

import danieldjgomes.larica.core.categoria.entity.CategoriaEntity;

import java.util.List;
import java.util.Optional;

public interface CategoriaPersist {

    CategoriaEntity createCategoria(CategoriaEntity categoria);

    Optional<CategoriaEntity> getCategoriaById(String id);

    List<CategoriaEntity> listAllCategorias();

    Optional<CategoriaEntity> updateCategoria(CategoriaEntity categoria);

    void disableCategoria(String id);
}
