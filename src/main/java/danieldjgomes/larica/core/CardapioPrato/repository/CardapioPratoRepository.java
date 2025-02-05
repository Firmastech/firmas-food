package danieldjgomes.larica.core.CardapioPrato.repository;


import danieldjgomes.larica.core.CardapioPrato.entity.CardapioPrato;
import danieldjgomes.larica.core.cardapio.entity.Cardapio;
import danieldjgomes.larica.core.prato.entity.Prato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardapioPratoRepository extends JpaRepository<CardapioPrato, String> {
    Optional<CardapioPrato> findByCardapioAndPrato(Cardapio cardapio, Prato prato);

}
