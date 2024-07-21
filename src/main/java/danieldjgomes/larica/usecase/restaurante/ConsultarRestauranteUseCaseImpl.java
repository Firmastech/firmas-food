package danieldjgomes.larica.usecase.restaurante;

import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;
import danieldjgomes.larica.ports.database.RestaurantePersist;
import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.usecase.restaurante.exceptions.RestauranteNotFoundException;
import danieldjgomes.larica.core.usecases.restaurante.ConsultarRestauranteUseCase;
import danieldjgomes.larica.adapter.mapper.RestauranteMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class ConsultarRestauranteUseCaseImpl implements ConsultarRestauranteUseCase {

    private final RestaurantePersist restaurantePersist;
    private final RestauranteMapper mapper;

    @Override
     public Restaurante consultar(String ID) {
        RestauranteModel entity = restaurantePersist.findById(ID).orElseThrow(
                ()-> new RestauranteNotFoundException("Restaurante nao encontrado")
        );
        return mapper.toRestaurante(entity);
     }
}
