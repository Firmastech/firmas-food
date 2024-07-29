package danieldjgomes.larica.app.adapter.database.restaurante.impl;

import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;
import danieldjgomes.larica.app.adapter.database.restaurante.repository.RestauranteRepository;
import danieldjgomes.larica.app.adapter.mapper.RestauranteMapper;
import danieldjgomes.larica.app.ports.database.RestaurantePersist;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@AllArgsConstructor
public class RestaurantePersistImpl implements RestaurantePersist {

    private final RestauranteRepository restauranteRepository;
    private final RestauranteMapper restauranteMapper;

    @Override
    public Optional<RestauranteEntity> findById(String id) {
        return restauranteRepository.findByIdAndAtivo(id,true);
    }

    @Override
    public Optional<RestauranteEntity> findByNome(String nome) {
        return restauranteRepository.findByNomeAndAtivo(nome,true);
    }

    @Override
    public RestauranteEntity save(RestauranteEntity restaurante) {
        RestauranteEntity entity = restaurante;
        entity.setAtivo(true);
        entity.setCriadoEm(new Date());
        return restauranteRepository.save(entity);
    }

    @Override
    public RestauranteEntity update(RestauranteEntity entityToUpdate) {
        entityToUpdate.setAtualizadoEm(new Date());
        return restauranteRepository.save(entityToUpdate);
    }

    @Override
    public void delete(RestauranteEntity entityToDelete) {
        entityToDelete.setDeletadoEm(new Date());
        entityToDelete.setAtivo(false);
        restauranteRepository.save(entityToDelete);
    }
}
