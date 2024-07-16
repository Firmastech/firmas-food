package danieldjgomes.larica.dataprovider.repository.impl;

import danieldjgomes.larica.core.restaurante.contract.RestauranteRepository;
import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.dataprovider.repository.RestauranteDao;
import danieldjgomes.larica.dataprovider.repository.RestauranteMapper;
import danieldjgomes.larica.dataprovider.repository.entity.RestauranteEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RestauranteRepositoryImpl implements RestauranteRepository {

    private final RestauranteDao restauranteDao;
    private final RestauranteMapper restauranteMapper;

    @Override
    public void save(Restaurante restaurante) {
        RestauranteEntity entity = restauranteMapper.from(restaurante);
        restauranteDao.save(entity);
    }
}
