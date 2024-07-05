package danieldjgomes.larica.infrastructure.keycloak;

import danieldjgomes.larica.infrastructure.keycloak.dto.CriarUsuarioRequestDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping(path = "api/usuarios")
public class KeycloakController {

    private final KeycloakService service;


    @PostMapping
    public ResponseEntity criarUsuario(@RequestBody @Valid CriarUsuarioRequestDTO userDTO) {
        int responseStatus = service.criarUsuario(userDTO).getStatus();
        HttpStatus status = HttpStatus.valueOf(responseStatus);
        return ResponseEntity.status(status).build();
    }

    @GetMapping
    private List<UserRepresentation> buscarUsuarios() {
        return service.buscarUsuarios();
    }


}
