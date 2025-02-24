package danieldjgomes.larica.app.adapter.database.categoria.repository;

import danieldjgomes.larica.core.categoria.entity.CategoriaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, String> {


    @Query("SELECT c FROM CategoriaEntity c WHERE c.ativo = true")
    List<CategoriaEntity> findAllAtivos();

    @Query("SELECT c FROM CategoriaEntity c WHERE c.id = :id AND c.ativo = true")
    Optional<CategoriaEntity> findCategoriaAtivoById(@Param("id") String id);

    @Transactional
    @Modifying
    @Query("""
            update CategoriaEntity c
            set c.deletado = :horarioAtual, c.atualizado = :horarioAtual, c.ativo = false
            where c.id = :id AND c.ativo = true
            """)
    void desativarCategoria(@Param("id") String id, @Param("horarioAtual") Date horarioAtual);

}

