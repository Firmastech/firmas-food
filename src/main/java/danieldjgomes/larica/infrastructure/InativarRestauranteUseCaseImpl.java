package danieldjgomes.larica.infrastructure;

import danieldjgomes.larica.core.restaurante.contract.RestauranteRepository;
import danieldjgomes.larica.core.restaurante.exceptions.EntityNotFoundException;
import danieldjgomes.larica.core.usecases.restaurante.InativarRestaurenteUseCase;
import danieldjgomes.larica.dataprovider.repository.entity.RestauranteEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class InativarRestauranteUseCaseImpl implements InativarRestaurenteUseCase {

    private final RestauranteRepository restauranteRepository;

    @Override
    public void inativarRestaurante(UUID ID) {
        RestauranteEntity entity = restauranteRepository.findById(ID).orElseThrow(
                ()-> new EntityNotFoundException("Restaurante nao encontrado")
        );
        restauranteRepository.delete(entity);
    }
}
