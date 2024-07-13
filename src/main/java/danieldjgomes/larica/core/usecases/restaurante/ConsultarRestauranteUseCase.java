package danieldjgomes.larica.core.usecases.restaurante;

import danieldjgomes.larica.infrastructure.dto.restaurante.response.ConsultarRestauranteResponseDTO;

public interface ConsultarRestauranteUseCase {

    ConsultarRestauranteResponseDTO consultar(final String ID);
}
