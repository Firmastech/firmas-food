package danieldjgomes.larica.app.adapter.database.cardapioPrato.repository;

import danieldjgomes.larica.app.adapter.database.cardapioPrato.model.CardapioPratoEntity;
import danieldjgomes.larica.app.usecase.cardapioPrato.request.CardapioPratoId;
import feign.Param;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardapioPratoRepository extends JpaRepository<CardapioPratoEntity, CardapioPratoId> {

    List<CardapioPratoEntity> findByCardapioId(String cardapioId);

    @Modifying
    @Transactional
    @Query("DELETE FROM CardapioPratoEntity cp WHERE cp.cardapioId = :cardapioId AND cp.pratoId IN :pratoIds")
    void deleteByCardapioIdAndPratoIdIn(@Param("cardapioId") String cardapioId, @Param("pratoIds") List<String> pratoIds);

}
