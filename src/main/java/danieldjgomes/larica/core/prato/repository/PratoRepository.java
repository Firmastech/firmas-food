package danieldjgomes.larica.core.prato.repository;

import danieldjgomes.larica.core.prato.entity.Prato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PratoRepository extends JpaRepository<Prato, Long> {
}
