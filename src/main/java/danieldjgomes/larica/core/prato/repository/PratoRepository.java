package danieldjgomes.larica.core.prato.repository;

import danieldjgomes.larica.core.prato.entity.PratoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PratoRepository extends JpaRepository<PratoEntity, String> {
}
