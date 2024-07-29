package danieldjgomes.larica.app.usecase.restaurante;

import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;
import danieldjgomes.larica.app.ports.database.RestaurantePersist;
import danieldjgomes.larica.app.usecase.restaurante.exceptions.RestauranteNotFoundException;
import danieldjgomes.larica.app.usecase.restaurante.port.InativarRestauranteUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InativarRestauranteUseCaseImpl implements InativarRestauranteUseCase {

    private final RestaurantePersist restaurantePersist;

    @Override
    public void inativarRestaurante(String ID) {
        RestauranteEntity entity = restaurantePersist.findById(ID).orElseThrow(
                ()-> new RestauranteNotFoundException("Restaurante nao encontrado")
        );
        restaurantePersist.delete(entity);
    }
}
