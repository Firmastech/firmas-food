package danieldjgomes.larica.app.adapter.controller;

import danieldjgomes.larica.app.usecase.token.response.TokenResponse;
import danieldjgomes.larica.app.usecase.token.request.LoginUsuarioRequest;
import danieldjgomes.larica.app.usecase.token.request.RevalidarTokenRequest;
import danieldjgomes.larica.app.usecase.token.BuscarTokenUsuarioUseCase;
import danieldjgomes.larica.app.usecase.token.RenovarTokenUsuarioUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
public class TokenController {

    private final BuscarTokenUsuarioUseCase buscarTokenUsuarioUseCase;

    private final RenovarTokenUsuarioUseCase renovarTokenUsuarioUseCase;

    @PostMapping(value = "/login")
    TokenResponse logarUsuario(@Valid @RequestBody LoginUsuarioRequest request) {
        return buscarTokenUsuarioUseCase.processar(request);
    }

    @PostMapping("/refresh")
    TokenResponse revalidarToken(@Valid @RequestBody RevalidarTokenRequest request) {
        return renovarTokenUsuarioUseCase.processar(request);
    }
}
