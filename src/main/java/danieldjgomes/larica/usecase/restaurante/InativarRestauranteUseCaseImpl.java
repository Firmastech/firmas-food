package danieldjgomes.larica.usecase.restaurante;

import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;
import danieldjgomes.larica.core.restaurante.contract.RestauranteRepository;
import danieldjgomes.larica.core.restaurante.exceptions.EntityNotFoundException;
import danieldjgomes.larica.core.usecases.restaurante.InativarRestauranteUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class InativarRestauranteUseCaseImpl implements InativarRestauranteUseCase {

    private final RestauranteRepository restauranteRepository;

    @Override
    public void inativarRestaurante(UUID ID) {
        RestauranteModel entity = restauranteRepository.findById(ID).orElseThrow(
                ()-> new EntityNotFoundException("Restaurante nao encontrado")
        );
        restauranteRepository.delete(entity);
    }
}
