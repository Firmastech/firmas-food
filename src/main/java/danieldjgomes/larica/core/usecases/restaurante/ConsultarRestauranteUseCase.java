package danieldjgomes.larica.core.usecases.restaurante;

import danieldjgomes.larica.core.restaurante.entity.Restaurante;

import java.util.UUID;

public interface ConsultarRestauranteUseCase {

    Restaurante consultar(final UUID ID);
}
