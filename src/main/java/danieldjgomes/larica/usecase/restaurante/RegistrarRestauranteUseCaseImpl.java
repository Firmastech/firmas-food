package danieldjgomes.larica.usecase.restaurante;

import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;
import danieldjgomes.larica.ports.database.RestaurantePersist;
import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.core.usecases.restaurante.RegistrarRestauranteUseCase;
import danieldjgomes.larica.adapter.mapper.RestauranteMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
@AllArgsConstructor
public class RegistrarRestauranteUseCaseImpl implements RegistrarRestauranteUseCase {

    private final RestaurantePersist restaurantePersist;
    private final RestauranteMapper mapper;

    public Restaurante registrarRestaurante(Restaurante restaurante) {
        restaurante.setId(UUID.randomUUID().toString());
        RestauranteModel entity = mapper.toEntity(restaurante);
        entity = restaurantePersist.save(entity);
        return mapper.toRestaurante(entity);
    }
}
