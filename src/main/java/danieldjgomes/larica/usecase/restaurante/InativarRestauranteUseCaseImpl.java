package danieldjgomes.larica.usecase.restaurante;

import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;
import danieldjgomes.larica.ports.database.RestaurantePersist;
import danieldjgomes.larica.usecase.restaurante.exceptions.RestauranteNotFoundException;
import danieldjgomes.larica.core.usecases.restaurante.InativarRestauranteUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class InativarRestauranteUseCaseImpl implements InativarRestauranteUseCase {

    private final RestaurantePersist restaurantePersist;

    @Override
    public void inativarRestaurante(String ID) {
        RestauranteModel entity = restaurantePersist.findById(ID).orElseThrow(
                ()-> new RestauranteNotFoundException("Restaurante nao encontrado")
        );
        restaurantePersist.delete(entity);
    }
}
