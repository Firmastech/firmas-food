package danieldjgomes.larica.infrastructure.token;

import danieldjgomes.larica.infrastructure.TokenKeycloakAutenticacao;
import danieldjgomes.larica.infrastructure.keycloak.RevalidarTokenKeycloakModel;
import danieldjgomes.larica.infrastructure.keycloak.dto.LoginUsuarioKeycloakModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@FeignClient(name = "keycloakClient", url = "${keycloak.token-url}")
public interface KeycloakUserClient {

    @PostMapping(value ="/token", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    TokenKeycloakAutenticacao getUserToken(@RequestBody LoginUsuarioKeycloakModel form);

    @PostMapping(value ="/token", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    TokenKeycloakAutenticacao refreshUserToken(@RequestBody RevalidarTokenKeycloakModel form);
}
