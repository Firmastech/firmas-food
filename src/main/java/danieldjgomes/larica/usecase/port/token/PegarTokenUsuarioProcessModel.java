package danieldjgomes.larica.usecase.port.token;

import danieldjgomes.larica.usecase.port.ProcessModel;
import danieldjgomes.larica.usecase.port.token.request.LoginUsuarioRequest;
import danieldjgomes.larica.usecase.port.token.response.TokenResponse;
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
