package danieldjgomes.larica.usecase.port.token;

import danieldjgomes.larica.usecase.port.token.response.TokenResponse;
import danieldjgomes.larica.usecase.port.token.request.RevalidarTokenRequest;

public interface RenovarTokenUsuarioUseCase {

    TokenResponse processar(RevalidarTokenRequest request);
}
