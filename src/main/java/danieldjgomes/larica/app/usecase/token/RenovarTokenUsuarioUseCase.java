package danieldjgomes.larica.app.usecase.token;

import danieldjgomes.larica.app.usecase.token.response.TokenResponse;
import danieldjgomes.larica.app.usecase.token.request.RevalidarTokenRequest;

public interface RenovarTokenUsuarioUseCase {

    TokenResponse processar(RevalidarTokenRequest request);
}
