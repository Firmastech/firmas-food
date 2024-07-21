package danieldjgomes.larica.adapter.controller;

import danieldjgomes.larica.infrastructure.keycloak.dto.CriarUsuarioRequestDTO;
import danieldjgomes.larica.usecase.port.usuario.CriarUsuarioUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "usuarios")
public class UsuarioController {

    private final CriarUsuarioUseCase criarUsuarioUseCase;

    @PostMapping
    public ResponseEntity criarUsuario(@RequestBody @Valid CriarUsuarioRequestDTO request) {
        criarUsuarioUseCase.processar(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
