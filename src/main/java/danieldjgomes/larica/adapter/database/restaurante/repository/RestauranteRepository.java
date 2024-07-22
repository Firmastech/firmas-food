package danieldjgomes.larica.adapter.database.restaurante.repository;


import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteModel, String> {

    Optional<RestauranteModel> findByNome(String nome);
}
