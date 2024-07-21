package danieldjgomes.larica.infrastructure.token;

import danieldjgomes.larica.infrastructure.keycloak.dto.TokenAutenticacaoKeycloakModelResponseDTO;
import danieldjgomes.larica.infrastructure.keycloak.dto.RenovarTokenUsuarioKeycloakModelDTO;
import danieldjgomes.larica.infrastructure.keycloak.dto.LoginUsuarioKeycloakModelDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@FeignClient(name = "keycloakClient", url = "${keycloak.token-url}")
public interface KeycloakUserClient {

    @PostMapping(value ="/token", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    TokenAutenticacaoKeycloakModelResponseDTO buscarTokenUsuario(@RequestBody LoginUsuarioKeycloakModelDTO form);

    @PostMapping(value ="/token", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    TokenAutenticacaoKeycloakModelResponseDTO revalidarTokenUsuario(@RequestBody RenovarTokenUsuarioKeycloakModelDTO form);
}
