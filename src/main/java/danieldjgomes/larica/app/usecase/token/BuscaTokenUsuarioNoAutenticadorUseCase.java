package danieldjgomes.larica.app.usecase.token;

import danieldjgomes.larica.app.usecase.usuario.request.external.LoginUsuarioKeycloakModelDTO;
import danieldjgomes.larica.app.usecase.usuario.request.external.TokenAutenticacaoKeycloakModelResponseDTO;
import danieldjgomes.larica.app.usecase.token.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuscaTokenUsuarioNoAutenticadorUseCase implements EtapaProcessoBuscarTokenUsuario {

    private final KeycloakUserClient keycloakUserClient;
    private final TokenMapper tokenMapper;
    private final String clientSecret = "**********";
    private final String clientId = "firmas-food";

    @Override
    public PegarTokenUsuarioProcessModel processar(PegarTokenUsuarioProcessModel process) {
        LoginUsuarioKeycloakModelDTO keycloakTokenRequest = buildModelBuscarTokenUsuarioKeycloak(process.getUsuarioId(), process.getEntrada().getSenha());
        TokenAutenticacaoKeycloakModelResponseDTO keycloakTokenResponse = keycloakUserClient.buscarTokenUsuario(keycloakTokenRequest);
        TokenResponse tokenResponse = tokenMapper.toLoginResponse(keycloakTokenResponse);
        process.setResultante(tokenResponse);
        return process;
    }

    private LoginUsuarioKeycloakModelDTO buildModelBuscarTokenUsuarioKeycloak(String usuarioId, String senha) {
        LoginUsuarioKeycloakModelDTO loginUsuarioKeycloakModelDTO = new LoginUsuarioKeycloakModelDTO();
        loginUsuarioKeycloakModelDTO.setClientSecret(clientSecret);
        loginUsuarioKeycloakModelDTO.setClientId(clientId);
        loginUsuarioKeycloakModelDTO.setGrantType("password");
        loginUsuarioKeycloakModelDTO.setUsername(usuarioId);
        loginUsuarioKeycloakModelDTO.setPassword(senha);
        return loginUsuarioKeycloakModelDTO;
    }

}
