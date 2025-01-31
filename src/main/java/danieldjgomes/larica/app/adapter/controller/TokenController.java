package danieldjgomes.larica.app.adapter.controller;

import danieldjgomes.larica.app.usecase.token.GerarTokenUsuarioUseCase;
import danieldjgomes.larica.app.usecase.token.RenovarTokenUsuarioUseCase;
import danieldjgomes.larica.app.usecase.token.request.LoginUsuarioRequest;
import danieldjgomes.larica.app.usecase.token.request.RevalidarTokenRequest;
import danieldjgomes.larica.app.usecase.token.response.TokenResponse;
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

    private final GerarTokenUsuarioUseCase gerarTokenUsuarioUseCase;

    private final RenovarTokenUsuarioUseCase renovarTokenUsuarioUseCase;

    @PostMapping(value = "/auth/login")
    TokenResponse logarUsuario(@Valid @RequestBody LoginUsuarioRequest request) {
        return gerarTokenUsuarioUseCase.processar(request);
    }

    @PostMapping(value = "/auth/refresh")
    TokenResponse renovarToken(@Valid @RequestBody RevalidarTokenRequest request) {
        return renovarTokenUsuarioUseCase.processar(request);
    }
}
