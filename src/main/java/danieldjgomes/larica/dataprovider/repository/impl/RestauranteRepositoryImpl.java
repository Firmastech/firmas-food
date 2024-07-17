package danieldjgomes.larica.dataprovider.repository.impl;

import danieldjgomes.larica.core.restaurante.contract.RestauranteRepository;
import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.dataprovider.repository.RestauranteDao;
import danieldjgomes.larica.dataprovider.repository.RestauranteMapper;
import danieldjgomes.larica.dataprovider.repository.entity.RestauranteEntity;
import danieldjgomes.larica.infrastructure.dto.restaurante.response.ConsultarRestauranteResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class RestauranteRepositoryImpl implements RestauranteRepository {

    private final RestauranteDao restauranteDao;
    private final RestauranteMapper restauranteMapper;

    @Override
    public Optional<RestauranteEntity> findById(UUID id) {
        return restauranteDao.findById(id);
    }

    @Override
    public Optional<RestauranteEntity> findByNome(String nome) {
        return restauranteDao.findByNome(nome);
    }

    @Override
    public void save(Restaurante restaurante) {
        RestauranteEntity entity = restauranteMapper.toEntity(restaurante);
        entity.setIsActive(true);
        entity.setDataInclusao(LocalDateTime.now());
        restauranteDao.save(entity);
    }

    @Override
    public RestauranteEntity update(RestauranteEntity entityToUpdate) {
        entityToUpdate.setDataAtualizacao(LocalDateTime.now());
        return restauranteDao.save(entityToUpdate);
    }

    @Override
    public void delete(RestauranteEntity entityToDelete) {
        entityToDelete.setDataExclusao(LocalDateTime.now());
        entityToDelete.setIsActive(false);
        restauranteDao.save(entityToDelete);
    }
}
