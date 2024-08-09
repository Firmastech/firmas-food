package danieldjgomes.larica.app.ports.database;

import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;

import java.util.Optional;

public interface RestaurantePersist {

    RestauranteEntity save(RestauranteEntity restaurante);

    Optional<RestauranteEntity> findById(String id);

    RestauranteEntity update(RestauranteEntity entity);

    void delete(RestauranteEntity entity);

    Optional<RestauranteEntity> findByNome(String nome);
}
