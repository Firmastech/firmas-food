package danieldjgomes.larica.app.adapter.controller;

import danieldjgomes.larica.app.usecase.usuario.request.CriarUsuarioRequestDTO;
import danieldjgomes.larica.app.usecase.usuario.CriarClienteUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final CriarClienteUseCase criarClienteUseCase;

    @PostMapping("/auth/register")
    public ResponseEntity criarUsuario(@RequestBody @Valid CriarUsuarioRequestDTO request) {
        criarClienteUseCase.processar(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
