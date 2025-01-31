package danieldjgomes.larica.app.usecase.usuario;


import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;

import java.util.Optional;

public interface BuscarUsuarioPorEmailERestauranteUseCase {

    Optional<UsuarioEntity> processar(String email, String restaurante);
}
