package danieldjgomes.larica.app.adapter.database.categoria.repository;

import danieldjgomes.larica.app.adapter.database.categoria.model.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, String> {

    @Query("SELECT c FROM CategoriaEntity c WHERE c.ativo = true")
    List<CategoriaEntity> findAllAtivos();

    @Query("SELECT c FROM CategoriaEntity c WHERE c.id = :id AND c.ativo = true")
    Optional<CategoriaEntity> findCategoriaAtivoById(@Param("id") String id);

}

