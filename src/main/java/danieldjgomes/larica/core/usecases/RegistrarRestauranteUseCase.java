package danieldjgomes.larica.core.usecases;

import danieldjgomes.larica.core.restaurante.contract.RestauranteRepository;
import danieldjgomes.larica.core.restaurante.entity.Restaurante;

import java.util.UUID;

public class RegistrarRestauranteUseCase{

    private final RestauranteRepository restauranteRepository;

    public RegistrarRestauranteUseCase(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public void registrarRestaurante(Restaurante restaurante) {
        restaurante.setId(UUID.randomUUID());
        restauranteRepository.save(restaurante);
    }
}
