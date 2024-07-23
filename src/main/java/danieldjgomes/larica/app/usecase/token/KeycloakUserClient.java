package danieldjgomes.larica.app.usecase.token;

import danieldjgomes.larica.app.usecase.usuario.request.external.TokenAutenticacaoKeycloakModelResponseDTO;
import danieldjgomes.larica.app.usecase.usuario.request.external.RenovarTokenUsuarioKeycloakModelDTO;
import danieldjgomes.larica.app.usecase.usuario.request.external.LoginUsuarioKeycloakModelDTO;
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
