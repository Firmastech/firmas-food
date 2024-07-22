package danieldjgomes.larica.core.prato.repository;

import danieldjgomes.larica.core.prato.entity.Prato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PratoRepository extends JpaRepository<Prato, String> {
}
