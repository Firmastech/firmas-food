package danieldjgomes.larica.infrastructure;

import danieldjgomes.larica.core.restaurante.contract.RestauranteRepository;
import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.core.usecases.restaurante.RegistrarRestauranteUseCase;
import danieldjgomes.larica.dataprovider.repository.entity.RestauranteEntity;
import danieldjgomes.larica.dataprovider.repository.mapper.RestauranteMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
@AllArgsConstructor
public class RegistrarRestauranteUseCaseImpl implements RegistrarRestauranteUseCase {

    private final RestauranteRepository restauranteRepository;
    private final RestauranteMapper mapper;

    public Restaurante registrarRestaurante(Restaurante restaurante) {
        restaurante.setId(UUID.randomUUID());
        RestauranteEntity entity = mapper.toEntity(restaurante);
        entity = restauranteRepository.save(entity);
        return mapper.toRestaurante(entity);
    }
}
