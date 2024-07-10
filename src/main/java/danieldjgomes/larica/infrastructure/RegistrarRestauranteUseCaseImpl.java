package danieldjgomes.larica.infrastructure;

import danieldjgomes.larica.core.restaurante.contract.RestauranteRepository;
import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.core.usecases.RegistrarRestauranteUseCase;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class RegistrarRestauranteUseCaseImpl implements RegistrarRestauranteUseCase {

    private final RestauranteRepository restauranteRepository;

    public RegistrarRestauranteUseCaseImpl(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public void registrarRestaurante(Restaurante restaurante) {
        restaurante.setId(UUID.randomUUID());
        restauranteRepository.save(restaurante);
    }
}
