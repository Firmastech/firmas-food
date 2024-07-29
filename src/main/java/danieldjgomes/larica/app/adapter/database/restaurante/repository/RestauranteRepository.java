package danieldjgomes.larica.app.adapter.database.restaurante.repository;


import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteEntity, String> {

    Optional<RestauranteEntity> findByNomeAndAtivo(String nome, boolean ativo);

    Optional<RestauranteEntity> findByIdAndAtivo(String id, boolean ativo);
}
