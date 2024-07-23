package danieldjgomes.larica.app.usecase.token;

import danieldjgomes.larica.app.usecase.token.request.RevalidarTokenRequest;
import danieldjgomes.larica.app.usecase.usuario.request.external.RenovarTokenUsuarioKeycloakModelDTO;
import danieldjgomes.larica.app.usecase.usuario.request.external.TokenAutenticacaoKeycloakModelResponseDTO;
import danieldjgomes.larica.app.usecase.token.response.TokenResponse;
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
