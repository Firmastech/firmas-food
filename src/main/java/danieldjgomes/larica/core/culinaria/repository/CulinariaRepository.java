package danieldjgomes.larica.core.culinaria.repository;

import danieldjgomes.larica.core.culinaria.entity.Culinaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CulinariaRepository extends JpaRepository<Culinaria, UUID> {
}
