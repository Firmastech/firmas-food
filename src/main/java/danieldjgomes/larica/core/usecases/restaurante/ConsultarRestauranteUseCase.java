package danieldjgomes.larica.core.usecases.restaurante;

import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.infrastructure.dto.restaurante.response.ConsultarRestauranteResponseDTO;

import java.util.UUID;

public interface ConsultarRestauranteUseCase {

    Restaurante consultar(final UUID ID);
}
