package danieldjgomes.larica.app.usecase.token;

import danieldjgomes.larica.app.usecase.token.request.LoginUsuarioRequest;
import danieldjgomes.larica.app.usecase.token.response.TokenResponse;

public interface BuscarTokenUsuarioUseCase {
    TokenResponse processar(LoginUsuarioRequest request);
}
