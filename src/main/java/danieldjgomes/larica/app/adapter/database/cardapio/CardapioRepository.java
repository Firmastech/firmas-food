package danieldjgomes.larica.app.adapter.database.cardapio;

import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface CardapioRepository extends JpaRepository<CardapioEntity, String> {

    Optional<CardapioEntity> findCardapioByIdAndAtivoTrue(String id);

    @Transactional
    @Modifying
    @Query("""
            update CardapioEntity c
            set c.deletado = :horarioAtual, c.atualizado = :horarioAtual, c.ativo = false
            where c.id = :id
            """)
    void desativarCardapio(String id, Date horarioAtual);
}
