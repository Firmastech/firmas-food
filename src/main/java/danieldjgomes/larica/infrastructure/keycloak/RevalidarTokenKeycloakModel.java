package danieldjgomes.larica.infrastructure.keycloak;

import lombok.Getter;

@Getter
public class RevalidarTokenKeycloakModel {

    private String client_id;
    private String grant_type;
    private String refresh_token;
    private String client_secret;

    public void setClientId(String clientId) {
        this.client_id = clientId;
    }

    public void setGrantType(String grantType) {
        this.grant_type = grantType;
    }

    public void setRefreshToken(String refreshToken) {
        this.refresh_token = refreshToken;
    }


    public void setClientSecret(String clientSecret) {
        this.client_secret = clientSecret;
    }
}
