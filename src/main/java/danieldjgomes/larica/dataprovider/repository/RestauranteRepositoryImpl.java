package danieldjgomes.larica.dataprovider.repository;

import danieldjgomes.larica.core.restaurante.contract.RestauranteRepository;
import danieldjgomes.larica.core.restaurante.entity.Restaurante;
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
    public void save(Restaurante restaurante) {
        RestauranteEntity entity = restauranteMapper.toEntity(restaurante);
        entity.setIsActive(true);
        entity.setDataInclusao(LocalDateTime.now());
        restauranteDao.save(entity);
    }

    @Override
    public ConsultarRestauranteResponseDTO findById(UUID id) {
        Optional<RestauranteEntity> entity = restauranteDao.findById(id);

        if(entity.isPresent()){
            ConsultarRestauranteResponseDTO dto = restauranteMapper.toDTO(entity.get());
            return dto;
        }

        return null;
    }
}
