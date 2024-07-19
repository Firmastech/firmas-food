package danieldjgomes.larica.core.restaurante.contract;

import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.dataprovider.repository.entity.RestauranteEntity;
import danieldjgomes.larica.infrastructure.dto.restaurante.response.ConsultarRestauranteResponseDTO;

import java.util.Optional;
import java.util.UUID;

public interface RestauranteRepository {

    RestauranteEntity save(RestauranteEntity restaurante);

    Optional<RestauranteEntity> findById(UUID id);

    RestauranteEntity update(RestauranteEntity entity);

    void delete(RestauranteEntity entity);

    Optional<RestauranteEntity> findByNome(String nome);
}
