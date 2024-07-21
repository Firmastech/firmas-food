package danieldjgomes.larica.usecase.port.token;

import danieldjgomes.larica.infrastructure.keycloak.dto.LoginUsuarioKeycloakModelDTO;
import danieldjgomes.larica.infrastructure.keycloak.dto.TokenAutenticacaoKeycloakModelResponseDTO;
import danieldjgomes.larica.infrastructure.token.KeycloakUserClient;
import danieldjgomes.larica.infrastructure.token.TokenMapper;
import danieldjgomes.larica.usecase.port.token.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuscaTokenUsuarioNoKeycloak implements EtapaProcessoBuscarTokenUsuario {

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
