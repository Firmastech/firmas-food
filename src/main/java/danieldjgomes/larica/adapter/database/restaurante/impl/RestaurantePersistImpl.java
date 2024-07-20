package danieldjgomes.larica.adapter.database.restaurante.impl;

import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;
import danieldjgomes.larica.adapter.database.restaurante.repository.RestauranteRepository;
import danieldjgomes.larica.adapter.mapper.RestauranteMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class RestaurantePersistImpl implements danieldjgomes.larica.core.restaurante.contract.RestauranteRepository {

    private final RestauranteRepository restauranteRepository;
    private final RestauranteMapper restauranteMapper;

    @Override
    public Optional<RestauranteModel> findById(UUID id) {
        return restauranteRepository.findById(id);
    }

    @Override
    public Optional<RestauranteModel> findByNome(String nome) {
        return restauranteRepository.findByNome(nome);
    }

    @Override
    public RestauranteModel save(RestauranteModel restaurante) {
        RestauranteModel entity = restaurante;
        entity.setIsActive(true);
        entity.setDataInclusao(LocalDateTime.now());
        return restauranteRepository.save(entity);
    }

    @Override
    public RestauranteModel update(RestauranteModel entityToUpdate) {
        entityToUpdate.setDataAtualizacao(LocalDateTime.now());
        return restauranteRepository.save(entityToUpdate);
    }

    @Override
    public void delete(RestauranteModel entityToDelete) {
        entityToDelete.setDataExclusao(LocalDateTime.now());
        entityToDelete.setIsActive(false);
        restauranteRepository.save(entityToDelete);
    }
}
