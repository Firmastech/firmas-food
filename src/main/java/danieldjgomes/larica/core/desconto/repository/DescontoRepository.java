package danieldjgomes.larica.core.desconto.repository;

import danieldjgomes.larica.core.desconto.entity.Desconto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescontoRepository extends JpaRepository<Desconto, Long> {
}
