package danieldjgomes.larica.infrastructure;

import danieldjgomes.larica.infrastructure.keycloak.KeycloakService;
import danieldjgomes.larica.infrastructure.model.LoginResponse;
import danieldjgomes.larica.infrastructure.model.LoginUsuarioRequest;
import danieldjgomes.larica.infrastructure.model.RevalidarTokenRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AccessController {

    private KeycloakService service;

    @PostMapping("/login")
    LoginResponse logarUsuario(@RequestBody LoginUsuarioRequest request){
        return service.logarUsuario(request.getUsuarioId(),request.getSenha());
    }

    @PostMapping("/refresh")
    LoginResponse revalidarToken(@RequestBody RevalidarTokenRequest request){
        return service.revalidarLogin(request);
    }
}
