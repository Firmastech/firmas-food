package danieldjgomes.larica.core.desconto.repository;

import danieldjgomes.larica.core.desconto.entity.Desconto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface DescontoRepository extends JpaRepository<Desconto, UUID> {

}
