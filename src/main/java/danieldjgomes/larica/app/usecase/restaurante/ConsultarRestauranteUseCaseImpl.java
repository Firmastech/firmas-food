package danieldjgomes.larica.app.usecase.restaurante;

import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;
import danieldjgomes.larica.app.ports.database.RestaurantePersist;
import danieldjgomes.larica.app.usecase.restaurante.response.Restaurante;
import danieldjgomes.larica.app.usecase.restaurante.exceptions.RestauranteNotFoundException;
import danieldjgomes.larica.app.usecase.restaurante.port.ConsultarRestauranteUseCase;
import danieldjgomes.larica.app.adapter.mapper.RestauranteMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConsultarRestauranteUseCaseImpl implements ConsultarRestauranteUseCase {

    private final RestaurantePersist restaurantePersist;
    private final RestauranteMapper mapper;

    @Override
     public Restaurante consultar(String ID) {
        RestauranteEntity entity = restaurantePersist.findById(ID).orElseThrow(
                ()-> new RestauranteNotFoundException()
        );
        return mapper.toRestaurante(entity);
     }
}
