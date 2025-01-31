package danieldjgomes.larica.app.usecase.token;

import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.adapter.database.pedidos.repository.UsuarioRepository;
import danieldjgomes.larica.app.usecase.token.exceptions.UsuarioNaoEncontradoException;
import danieldjgomes.larica.app.usecase.token.request.RevalidarTokenRequest;
import danieldjgomes.larica.app.usecase.token.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class RenovarTokenUsuarioUseCaseImpl implements RenovarTokenUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final ValidarRestauranteNoTokenUseCase validarRestauranteNoTokenUseCase;
    private final ValidarEmailNoTokenUseCase validarEmailNoTokenUseCase;
    private final MontarTokenJWTUseCase montarTokenJWTUseCase;

    @Override
    public TokenResponse processar(RevalidarTokenRequest request) {

        String email = validarEmailNoTokenUseCase.validar(request.getToken());
        String restauranteId = validarRestauranteNoTokenUseCase.validar(request.getToken());
        Optional<UsuarioEntity> usuario = usuarioRepository.findAllByRestauranteIdAndEmailAndAtivoTrue(restauranteId, email);
        if (usuario.isPresent()) {
            return montarTokenJWTUseCase.montar(usuario.get());
        }
        throw new UsuarioNaoEncontradoException();
    }

}
