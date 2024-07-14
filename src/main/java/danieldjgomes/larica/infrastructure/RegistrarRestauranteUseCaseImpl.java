package danieldjgomes.larica.infrastructure;

import danieldjgomes.larica.core.restaurante.contract.RestauranteRepository;
import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.core.usecases.restaurante.RegistrarRestauranteUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
@AllArgsConstructor
public class RegistrarRestauranteUseCaseImpl implements RegistrarRestauranteUseCase {

    private final RestauranteRepository restauranteRepository;

    public void registrarRestaurante(Restaurante restaurante) {
        restaurante.setId(UUID.randomUUID());
        restauranteRepository.save(restaurante);
    }
}
