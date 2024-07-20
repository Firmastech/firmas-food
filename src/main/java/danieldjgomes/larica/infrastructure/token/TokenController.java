package danieldjgomes.larica.infrastructure.token;

import danieldjgomes.larica.infrastructure.keycloak.KeycloakService;
import danieldjgomes.larica.infrastructure.token.model.LoginResponse;
import danieldjgomes.larica.infrastructure.token.model.LoginUsuarioRequest;
import danieldjgomes.larica.infrastructure.token.model.RevalidarTokenRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
public class TokenController {

    private KeycloakService service;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    LoginResponse logarUsuario(@Valid @RequestBody LoginUsuarioRequest request){
        return service.logarUsuario(request.getUsuarioId(),request.getSenha());
    }

    @PostMapping("/refresh")
    LoginResponse revalidarToken(@Valid @RequestBody RevalidarTokenRequest request){
        return service.revalidarToken(request);
    }
}
