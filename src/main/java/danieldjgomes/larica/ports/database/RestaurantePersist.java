package danieldjgomes.larica.ports.database;

import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;

import java.util.Optional;

public interface RestaurantePersist {

    RestauranteModel save(RestauranteModel restaurante);

    Optional<RestauranteModel> findById(String id);

    RestauranteModel update(RestauranteModel entity);

    void delete(RestauranteModel entity);

    Optional<RestauranteModel> findByNome(String nome);
}
