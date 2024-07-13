package danieldjgomes.larica.core.restaurante.contract;

import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.infrastructure.dto.restaurante.response.ConsultarRestauranteResponseDTO;

public interface RestauranteRepository {

    void save(Restaurante restaurante);

    ConsultarRestauranteResponseDTO findById(String id);
}
