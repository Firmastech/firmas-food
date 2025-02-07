package danieldjgomes.larica.app.adapter.database.restaurante.impl;

import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;
import danieldjgomes.larica.app.adapter.database.restaurante.repository.RestauranteRepository;
import danieldjgomes.larica.app.adapter.mapper.RestauranteMapper;
import danieldjgomes.larica.app.ports.database.RestaurantePersist;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@AllArgsConstructor
public class RestaurantePersistImpl implements RestaurantePersist {

    private final RestauranteRepository restauranteRepository;
    private final RestauranteMapper restauranteMapper;

    @Override
    public Optional<RestauranteEntity> findById(String id) {
        return restauranteRepository.findByIdAndAtivoIsTrue(id);
    }

    @Override
    public Optional<RestauranteEntity> findByNome(String nome) {
        return restauranteRepository.findByNomeAndAtivoIsTrue(nome);
    }

    @Override
    public RestauranteEntity save(RestauranteEntity restaurante) {
        RestauranteEntity entity = restaurante;
        entity.setAtivo(true);
        entity.setCriadoEm(LocalDateTime.now());
        return restauranteRepository.save(entity);
    }

    @Override
    public RestauranteEntity update(RestauranteEntity entityToUpdate) {
        entityToUpdate.setAtualizadoEm(LocalDateTime.now());
        return restauranteRepository.save(entityToUpdate);
    }

    @Override
    public void delete(RestauranteEntity entityToDelete) {
        entityToDelete.setDeletadoEm(LocalDateTime.now());
        entityToDelete.setAtivo(false);
        restauranteRepository.save(entityToDelete);
    }
}
