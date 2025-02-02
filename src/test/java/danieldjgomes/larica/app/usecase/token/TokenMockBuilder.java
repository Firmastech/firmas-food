package danieldjgomes.larica.app.usecase.token;

import danieldjgomes.larica.app.usecase.token.request.LoginUsuarioRequest;
import danieldjgomes.larica.app.usecase.token.response.TokenResponse;

import java.util.*;

public class TokenMockBuilder {

    public PegarTokenUsuarioProcessModel gerarProcessModel() {
        LoginUsuarioRequest validLoginRequest;
        validLoginRequest = new LoginUsuarioRequest();
        validLoginRequest.setEmail("user@email.com");
        validLoginRequest.setSenha("validPassword");
        PegarTokenUsuarioProcessModel processModel = new PegarTokenUsuarioProcessModel(validLoginRequest);
        processModel.setUsuarioId("6f19009d-c91b-4106-8150-66f9bdd1a3ed");
        return processModel;
    }

        public LoginUsuarioRequest gerarRequestLoginValido() {
        LoginUsuarioRequest validLoginRequest = new LoginUsuarioRequest();
        validLoginRequest.setEmail("validUser@email.com");
        validLoginRequest.setSenha("validPassword");
        validLoginRequest.setRestaurante("123e4567-e89b-12d3-a456-426614174000");
        return validLoginRequest;
    }

    public TokenResponse geraTokenResponseValido() {
        return new TokenResponse("accessTokenValue","refreshTokenValue");
    }

}
