package danieldjgomes.larica.core.cardapioPrato.repository;

import danieldjgomes.larica.core.cardapioPrato.entity.CardapioPrato;
import danieldjgomes.larica.core.cardapioPrato.request.CardapioPratoId;
import feign.Param;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardapioPratoRepository extends JpaRepository<CardapioPrato, CardapioPratoId> {

    List<CardapioPrato> findByCardapioId(String cardapioId);

    @Modifying
    @Transactional
    @Query("DELETE FROM CardapioPrato cp WHERE cp.cardapioId = :cardapioId AND cp.pratoId IN :pratoIds")
    void deleteByCardapioIdAndPratoIdIn(@Param("cardapioId") String cardapioId, @Param("pratoIds") List<String> pratoIds);

}
