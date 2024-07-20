package danieldjgomes.larica.adapter.database.restaurante.repository;


import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteModel, UUID> {

    Optional<RestauranteModel> findByNome(String nome);
}
