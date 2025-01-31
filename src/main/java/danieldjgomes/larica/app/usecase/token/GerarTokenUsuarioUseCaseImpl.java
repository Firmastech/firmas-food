package danieldjgomes.larica.app.usecase.token;

import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.usecase.token.request.LoginUsuarioRequest;
import danieldjgomes.larica.app.usecase.token.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GerarTokenUsuarioUseCaseImpl implements GerarTokenUsuarioUseCase {

    private final AuthenticationManager authenticationManager;
    private final MontarTokenJWTUseCase montarTokenJWTUseCase;

    @Override
    public TokenResponse processar(LoginUsuarioRequest request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.getEmail() + ":" + request.getRestaurante(), request.getSenha());
        Authentication authenticate = this.authenticationManager.authenticate(usernamePassword);
        UsuarioEntity usuario = (UsuarioEntity) authenticate.getPrincipal();
        return montarTokenJWTUseCase.montar(usuario);
    }


}
