package danieldjgomes.larica.core.cardapio.repository;

import danieldjgomes.larica.core.cardapio.entity.Cardapio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardapioRepository extends JpaRepository<Cardapio, Long> {
}
