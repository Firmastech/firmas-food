package danieldjgomes.larica.usecase.restaurante;

import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;
import danieldjgomes.larica.core.restaurante.contract.RestauranteRepository;
import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.core.usecases.restaurante.RegistrarRestauranteUseCase;
import danieldjgomes.larica.adapter.mapper.RestauranteMapper;
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
        RestauranteModel entity = mapper.toEntity(restaurante);
        entity = restauranteRepository.save(entity);
        return mapper.toRestaurante(entity);
    }
}
