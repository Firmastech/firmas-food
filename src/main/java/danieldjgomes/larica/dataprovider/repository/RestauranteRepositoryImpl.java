package danieldjgomes.larica.dataprovider.repository;

import danieldjgomes.larica.core.restaurante.contract.RestauranteRepository;
import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.dataprovider.repository.entity.RestauranteEntity;
import danieldjgomes.larica.infrastructure.dto.restaurante.response.ConsultarRestauranteResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

    @Override
    public ConsultarRestauranteResponseDTO findById(String id) {
        Optional<RestauranteEntity> entity = restauranteDao.findById(id);

        if(entity.isPresent()){
        ConsultarRestauranteResponseDTO dto = restauranteMapper.to(entity.get());
        return dto;
        }

        return null;
    }
}
