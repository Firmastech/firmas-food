package danieldjgomes.larica.core.cardapio.repository;

import danieldjgomes.larica.core.cardapio.entity.Cardapio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardapioRepository extends JpaRepository<Cardapio, UUID> {
}
