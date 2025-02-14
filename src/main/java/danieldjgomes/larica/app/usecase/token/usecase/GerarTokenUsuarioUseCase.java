package danieldjgomes.larica.app.usecase.token.usecase;

import danieldjgomes.larica.app.usecase.token.request.LoginUsuarioRequest;
import danieldjgomes.larica.app.usecase.token.response.TokenResponse;

public interface GerarTokenUsuarioUseCase {
    TokenResponse processar(LoginUsuarioRequest request);
}
