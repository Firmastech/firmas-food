package danieldjgomes.larica.core.categoria.repository;

import danieldjgomes.larica.core.categoria.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, String> {

}

