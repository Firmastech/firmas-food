package danieldjgomes.larica.core.usecases.restaurante;

import danieldjgomes.larica.infrastructure.dto.restaurante.response.ConsultarRestauranteResponseDTO;

import java.util.UUID;

public interface ConsultarRestauranteUseCase {

    ConsultarRestauranteResponseDTO consultar(final UUID ID);
}
