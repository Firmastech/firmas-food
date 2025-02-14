package danieldjgomes.larica.app.usecase.token.usecase;

import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.usecase.token.response.TokenResponse;

public interface MontarTokenJWTUseCase {

    TokenResponse montar(UsuarioEntity usuario);
}
