package danieldjgomes.larica.app.usecase.token;

import danieldjgomes.larica.app.usecase.token.request.LoginUsuarioRequest;
import danieldjgomes.larica.app.usecase.token.response.TokenResponse;

public class TokenMockBuilder {


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
