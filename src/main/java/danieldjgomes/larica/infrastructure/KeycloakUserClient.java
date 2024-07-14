package danieldjgomes.larica.infrastructure;

import danieldjgomes.larica.infrastructure.keycloak.RevalidarTokenKeycloakModel;
import danieldjgomes.larica.infrastructure.keycloak.dto.LoginUsuarioKeycloakModel;
import danieldjgomes.larica.infrastructure.model.RevalidarTokenRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@FeignClient(name = "keycloakClient", url = "${spring.security.oauth2.client.provider.keycloak.issuer-uri}/protocol/openid-connect")
public interface KeycloakUserClient {

    @PostMapping(value ="/token", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    TokenKeycloakAutenticacao getUserToken(@RequestBody LoginUsuarioKeycloakModel form);

    @PostMapping(value ="/token", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    TokenKeycloakAutenticacao refreshToken(@RequestBody RevalidarTokenKeycloakModel form);
}
