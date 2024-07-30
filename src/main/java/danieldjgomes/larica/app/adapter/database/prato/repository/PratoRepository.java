package danieldjgomes.larica.app.adapter.database.prato.repository;

import danieldjgomes.larica.app.adapter.database.prato.model.PratoEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PratoRepository extends JpaRepository<PratoEntity, String> {

    @Query("SELECT p FROM PratoEntity p WHERE p.estaAtivo = true")
    List<PratoEntity> findAllAtivos();

    @Query("SELECT p FROM PratoEntity p WHERE p.id = :id AND p.estaAtivo = true")
    Optional<PratoEntity> findPratoAtivoById(@Param("id") String id);

    @Transactional
    @Modifying
    @Query("""
            update PratoEntity p
            set p.nome = :nome,
                p.descricao = :descricao,
                p.preco = :preco,
                p.categoria = :categoriaEntity,
                p.atualizado = CURRENT_TIMESTAMP
            where p.id = :id AND p.estaAtivo = true
            """)
    Integer atualizarPrato(@Param("id") String id,
                       @Param("nome") String nome,
                       @Param("descricao") String descricao,
                       @Param("preco") BigDecimal preco,
                       @Param("categoriaEntity") String categoriaEntity);

    @Transactional
    @Modifying
    @Query("""
            update PratoEntity p
            set p.deletado = :horarioAtual, p.atualizado = :horarioAtual, p.estaAtivo = false
            where p.id = :id AND p.estaAtivo = true
            """)
    void desativarPrato(@Param("id") String id, @Param("horarioAtual") Date horarioAtual);
}
