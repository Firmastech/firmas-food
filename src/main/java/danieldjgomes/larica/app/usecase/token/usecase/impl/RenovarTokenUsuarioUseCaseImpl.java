package danieldjgomes.larica.app.usecase.token.usecase.impl;

import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.adapter.database.pedidos.repository.UsuarioRepository;
import danieldjgomes.larica.app.usecase.token.exceptions.ErroAoBuscarUsuarioERestauranteNaRevalidacaoDeTokenException;
import danieldjgomes.larica.app.usecase.token.request.RevalidarTokenRequest;
import danieldjgomes.larica.app.usecase.token.response.TokenResponse;
import danieldjgomes.larica.app.usecase.token.usecase.*;
import danieldjgomes.larica.infrastructure.TokenRevalidado;
import danieldjgomes.larica.infrastructure.TokenRevalidadoRepository;
import danieldjgomes.larica.infrastructure.config.HMACEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class RenovarTokenUsuarioUseCaseImpl implements RenovarTokenUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final ValidarRestauranteNoTokenUseCase validarRestauranteNoTokenUseCase;
    private final ValidarEmailNoTokenUseCase validarEmailNoTokenUseCase;
    private final MontarTokenJWTUseCase montarTokenJWTUseCase;
    private final TokenRevalidadoRepository tokenRevalidadoRepository;
    private final PersistirTokenRevalidadoUseCase persistirTokenRevalidadoUseCase;
    private final HMACEncoder hmacEncoder;

    @Override
    public TokenResponse processar(RevalidarTokenRequest request) {

        String email = validarEmailNoTokenUseCase.validar(request.getToken());
        String restauranteId = validarRestauranteNoTokenUseCase.validar(request.getToken());
        Optional<UsuarioEntity> usuario = usuarioRepository.findAllByRestauranteIdAndEmailAndAtivoTrue(restauranteId, email);

        if (usuario.isEmpty()) {
            throw new ErroAoBuscarUsuarioERestauranteNaRevalidacaoDeTokenException(email, restauranteId);
        }

        TokenRevalidado tokenRevalidado = tokenRevalidadoRepository.findByTokenAndExpiracaoAfter(
                        hmacEncoder.encode(request.getToken()), LocalDateTime.now())
                .orElseThrow(RevalidarTokenInvalidoException::new);

        tokenRevalidadoRepository.delete(tokenRevalidado);

        TokenResponse tokenResponse = montarTokenJWTUseCase.montar(usuario.get());
        persistirTokenRevalidadoUseCase.persistir(usuario.get(), tokenResponse);
        return tokenResponse;
    }
}


