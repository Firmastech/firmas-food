package danieldjgomes.larica.app.adapter.database.cardapio;

import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioResumidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardapioResumidoRepository extends JpaRepository<CardapioResumidoEntity, String> {

    List<CardapioResumidoEntity> findAllByRestauranteIdAndEstaAtivoIsTrue(String restauranteId);

}
