package danieldjgomes.larica.adapter.database.restaurante.impl;

import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;
import danieldjgomes.larica.adapter.database.restaurante.repository.RestauranteRepository;
import danieldjgomes.larica.adapter.mapper.RestauranteMapper;
import danieldjgomes.larica.ports.database.RestaurantePersist;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class RestaurantePersistImpl implements RestaurantePersist {

    private final RestauranteRepository restauranteRepository;
    private final RestauranteMapper restauranteMapper;

    @Override
    public Optional<RestauranteModel> findById(String id) {
        return restauranteRepository.findByIdAndAtivo(id,true);
    }

    @Override
    public Optional<RestauranteModel> findByNome(String nome) {
        return restauranteRepository.findByNomeAndAtivo(nome,true);
    }

    @Override
    public RestauranteModel save(RestauranteModel restaurante) {
        RestauranteModel entity = restaurante;
        entity.setAtivo(true);
        entity.setCriadoEm(new Date());
        return restauranteRepository.save(entity);
    }

    @Override
    public RestauranteModel update(RestauranteModel entityToUpdate) {
        entityToUpdate.setAtualizadoEm(new Date());
        return restauranteRepository.save(entityToUpdate);
    }

    @Override
    public void delete(RestauranteModel entityToDelete) {
        entityToDelete.setDeletadoEm(new Date());
        entityToDelete.setAtivo(false);
        restauranteRepository.save(entityToDelete);
    }
}
