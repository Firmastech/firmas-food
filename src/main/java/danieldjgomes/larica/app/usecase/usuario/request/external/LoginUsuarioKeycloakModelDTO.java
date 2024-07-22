package danieldjgomes.larica.app.usecase.usuario.request.external;

import lombok.Getter;

@Getter
public class LoginUsuarioKeycloakModelDTO {
    private String client_secret;
    private String client_id;
    private String grant_type;
    private String username;

    private String password;

    public void setClientSecret(String clientSecret) {
        this.client_secret = clientSecret;
    }

    public void setClientId(String clientId) {
        this.client_id = clientId;
    }

    public void setGrantType(String grantType) {
        this.grant_type = grantType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
