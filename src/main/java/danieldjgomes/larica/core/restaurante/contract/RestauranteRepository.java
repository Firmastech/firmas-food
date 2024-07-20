package danieldjgomes.larica.core.restaurante.contract;

import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;

import java.util.Optional;
import java.util.UUID;

public interface RestauranteRepository {

    RestauranteModel save(RestauranteModel restaurante);

    Optional<RestauranteModel> findById(UUID id);

    RestauranteModel update(RestauranteModel entity);

    void delete(RestauranteModel entity);

    Optional<RestauranteModel> findByNome(String nome);
}
