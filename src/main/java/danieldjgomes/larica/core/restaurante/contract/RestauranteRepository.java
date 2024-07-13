package danieldjgomes.larica.core.restaurante.contract;

import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.infrastructure.dto.restaurante.response.ConsultarRestauranteResponseDTO;
import java.util.UUID;

public interface RestauranteRepository {

    void save(Restaurante restaurante);

    ConsultarRestauranteResponseDTO findById(UUID id);
}
