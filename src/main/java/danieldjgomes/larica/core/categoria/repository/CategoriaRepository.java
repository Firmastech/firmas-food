package danieldjgomes.larica.core.categoria.repository;

import danieldjgomes.larica.core.categoria.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, String> {

}

