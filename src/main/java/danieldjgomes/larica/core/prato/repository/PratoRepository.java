package danieldjgomes.larica.core.prato.repository;

import danieldjgomes.larica.core.prato.entity.Prato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PratoRepository extends JpaRepository<Prato, UUID> {
}
