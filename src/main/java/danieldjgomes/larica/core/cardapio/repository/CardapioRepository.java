package danieldjgomes.larica.core.cardapio.repository;

import danieldjgomes.larica.core.cardapio.entity.Cardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CardapioRepository extends JpaRepository<Cardapio, String> {
}
