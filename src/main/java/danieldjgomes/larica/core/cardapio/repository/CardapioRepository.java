package danieldjgomes.larica.core.cardapio.repository;

import danieldjgomes.larica.core.cardapio.entity.CardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardapioRepository extends JpaRepository<CardapioEntity, String> {

    Optional<CardapioEntity> findByIdAndAtivoTrue(String id);
}
