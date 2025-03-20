package danieldjgomes.larica.app.usecase.token.usecase.impl;

import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.usecase.token.request.LoginUsuarioRequest;
import danieldjgomes.larica.app.usecase.token.response.TokenResponse;
import danieldjgomes.larica.app.usecase.token.usecase.GerarTokenUsuarioUseCase;
import danieldjgomes.larica.app.usecase.token.usecase.MontarTokenJWTUseCase;
import danieldjgomes.larica.app.usecase.token.usecase.PersistirTokenRevalidadoUseCase;
import danieldjgomes.larica.infrastructure.TokenRevalidado;
import danieldjgomes.larica.infrastructure.TokenRevalidadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
@RequiredArgsConstructor
public class GerarTokenUsuarioUseCaseImpl implements GerarTokenUsuarioUseCase {

    private final AuthenticationManager authenticationManager;
    private final MontarTokenJWTUseCase montarTokenJWTUseCase;
    private final PersistirTokenRevalidadoUseCase persistirTokenRevalidadoUseCase;


    @Override
    public TokenResponse processar(LoginUsuarioRequest request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.getEmail() + ":" + request.getRestaurante(), request.getSenha());
        Authentication authenticate = this.authenticationManager.authenticate(usernamePassword);
        UsuarioEntity usuario = (UsuarioEntity) authenticate.getPrincipal();
        TokenResponse token = montarTokenJWTUseCase.montar(usuario);

        persistirTokenRevalidadoUseCase.persistir(usuario,token);

        return token;
    }


}
