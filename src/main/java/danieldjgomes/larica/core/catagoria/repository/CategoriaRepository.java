package danieldjgomes.larica.core.catagoria.repository;

import danieldjgomes.larica.core.catagoria.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, String> {

}

