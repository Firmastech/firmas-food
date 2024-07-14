package danieldjgomes.larica.infrastructure.keycloak;


import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static org.keycloak.OAuth2Constants.CLIENT_CREDENTIALS;

@Service
public class KeycloakAdminBuilder {

    @Value("${keycloak.credentials.secret}")
    private String secretKey;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.auth-server-url}")
    private String authUrl;

    @Value("${keycloak.realm}")
    private String realm;


    public RealmResource getKeycloak() {
        return KeycloakBuilder.builder()
                .grantType(CLIENT_CREDENTIALS)
                .serverUrl(authUrl)
                .clientId(clientId)
                .clientSecret(secretKey)
                .build()
                .realm(realm);
    }
}