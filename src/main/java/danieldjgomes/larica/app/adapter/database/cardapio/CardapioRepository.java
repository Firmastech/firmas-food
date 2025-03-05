package danieldjgomes.larica.app.adapter.database.cardapio;

import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardapioRepository extends JpaRepository<CardapioEntity, String> {

    Page<CardapioEntity> findAllByRestauranteIdAndAtivoTrue(String restauranteId, Pageable pageable);
    Optional<CardapioEntity> findByIdAndRestauranteIdAndAtivoTrue(String cardapioId, String restauranteId);
    Optional<CardapioEntity> findFirstByRestauranteIdAndAtivoTrue(String restauranteId);
}
