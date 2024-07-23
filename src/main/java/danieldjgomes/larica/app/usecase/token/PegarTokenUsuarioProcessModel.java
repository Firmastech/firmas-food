package danieldjgomes.larica.app.usecase.token;

import danieldjgomes.larica.app.usecase.ProcessModel;
import danieldjgomes.larica.app.usecase.token.request.LoginUsuarioRequest;
import danieldjgomes.larica.app.usecase.token.response.TokenResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PegarTokenUsuarioProcessModel extends ProcessModel<LoginUsuarioRequest, TokenResponse> {

    private String usuarioId;

    public PegarTokenUsuarioProcessModel(LoginUsuarioRequest entrada) {
        super(entrada);
    }
}
