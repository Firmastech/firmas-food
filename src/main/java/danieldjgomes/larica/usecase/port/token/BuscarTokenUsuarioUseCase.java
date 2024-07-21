package danieldjgomes.larica.usecase.port.token;

import danieldjgomes.larica.usecase.port.token.request.LoginUsuarioRequest;
import danieldjgomes.larica.usecase.port.token.response.TokenResponse;

public interface BuscarTokenUsuarioUseCase {
    TokenResponse processar(LoginUsuarioRequest request);
}
