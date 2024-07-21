package danieldjgomes.larica.usecase.port.token;

import danieldjgomes.larica.infrastructure.keycloak.dto.RenovarTokenUsuarioKeycloakModelDTO;
import danieldjgomes.larica.infrastructure.keycloak.dto.TokenAutenticacaoKeycloakModelResponseDTO;
import danieldjgomes.larica.infrastructure.token.KeycloakUserClient;
import danieldjgomes.larica.infrastructure.token.TokenMapper;
import danieldjgomes.larica.usecase.port.token.response.TokenResponse;
import danieldjgomes.larica.usecase.port.token.request.RevalidarTokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RenovarTokenUsuarioUseCaseImpl implements RenovarTokenUsuarioUseCase {

    private final KeycloakUserClient keycloakUserClient;
    private final TokenMapper tokenMapper;

    @Value("${spring.security.oauth2.client.registration.keycloak.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
    private String clientId;

    @Override
    public TokenResponse processar(RevalidarTokenRequest request) {
        RenovarTokenUsuarioKeycloakModelDTO keycloakTokenRequest = buildModelRenovarTokenUsuarioKeycloak(request);

        TokenAutenticacaoKeycloakModelResponseDTO keycloakTokenResponse = keycloakUserClient.revalidarTokenUsuario(keycloakTokenRequest);
        return tokenMapper.toLoginResponse(keycloakTokenResponse);
    }


    private RenovarTokenUsuarioKeycloakModelDTO buildModelRenovarTokenUsuarioKeycloak(RevalidarTokenRequest revalidarToken) {
        RenovarTokenUsuarioKeycloakModelDTO renovarTokenUsuarioKeycloakModelDTO = new RenovarTokenUsuarioKeycloakModelDTO();
        renovarTokenUsuarioKeycloakModelDTO.setRefreshToken(revalidarToken.getToken());
        renovarTokenUsuarioKeycloakModelDTO.setClientId(clientId);
        renovarTokenUsuarioKeycloakModelDTO.setClientSecret(clientSecret);
        renovarTokenUsuarioKeycloakModelDTO.setGrantType("refresh_token");
        return renovarTokenUsuarioKeycloakModelDTO;
    }
}
