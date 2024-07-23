package danieldjgomes.larica.core.cardapioPrato.repository;

import danieldjgomes.larica.core.cardapioPrato.entity.CardapioPrato;
import danieldjgomes.larica.core.cardapioPrato.request.CardapioPratoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardapioPratoRepository extends JpaRepository<CardapioPrato, CardapioPratoId> {

    List<CardapioPrato> findByCardapioId(String cardapioId);
    List<CardapioPrato> findByCardapioIdAndPratoIdIn(String cardapioId, List<String> pratoIds);

}
