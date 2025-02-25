package danieldjgomes.larica.app.adapter.database.cardapio;

import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface CardapioRepository extends JpaRepository<CardapioEntity, String> {

    Optional<CardapioEntity> findCardapioByIdAndRestauranteIdAndAtivoTrue(String id, String restauranteId);

    Optional<CardapioEntity> findFirstByRestauranteIdAndAtivoTrue(String restauranteId);
}
