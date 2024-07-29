package danieldjgomes.larica.app.usecase.restaurante.port;

import danieldjgomes.larica.app.usecase.restaurante.response.Restaurante;

public interface ConsultarRestauranteUseCase {

    Restaurante consultar(final String ID);
}
