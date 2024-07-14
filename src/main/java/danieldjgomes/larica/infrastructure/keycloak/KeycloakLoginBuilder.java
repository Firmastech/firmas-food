package danieldjgomes.larica.infrastructure.keycloak;


import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.token.TokenManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static org.keycloak.OAuth2Constants.CLIENT_CREDENTIALS;

@Service
public class KeycloakLoginBuilder {

    @Value("${spring.security.oauth2.client.registration.keycloak.client-secret}")
    private String secretKey;

    @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.auth-server-url}")
    private String authUrl;

    @Value("${keycloak.realm}")
    private String realm;


    public TokenManager getTokenManager(String usuarioId, String senha) {
        return KeycloakBuilder.builder()
                .grantType(CLIENT_CREDENTIALS)
                .serverUrl(authUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(secretKey)
                .username(usuarioId)
                .password(senha)
                .build()
                .tokenManager();

    }
}