package danieldjgomes.larica.dataprovider.repository;

import danieldjgomes.larica.core.restaurante.contract.RestauranteRepository;
import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.dataprovider.repository.entity.RestauranteEntity;
import danieldjgomes.larica.dataprovider.repository.mapper.RestauranteMapper;
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
    public RestauranteEntity save(RestauranteEntity restaurante) {
        RestauranteEntity entity = restaurante;
        entity.setIsActive(true);
        entity.setDataInclusao(LocalDateTime.now());
        return restauranteDao.save(entity);
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
