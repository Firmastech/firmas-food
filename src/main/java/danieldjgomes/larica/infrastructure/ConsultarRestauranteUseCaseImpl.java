package danieldjgomes.larica.infrastructure;

import danieldjgomes.larica.core.restaurante.contract.RestauranteRepository;
import danieldjgomes.larica.core.usecases.restaurante.ConsultarRestauranteUseCase;
import danieldjgomes.larica.infrastructure.dto.restaurante.response.ConsultarRestauranteResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConsultarRestauranteUseCaseImpl implements ConsultarRestauranteUseCase {

    private final RestauranteRepository restauranteRepository;

    @Override
     public ConsultarRestauranteResponseDTO consultar(String ID) {
        return restauranteRepository.findById(ID);
     }
}
