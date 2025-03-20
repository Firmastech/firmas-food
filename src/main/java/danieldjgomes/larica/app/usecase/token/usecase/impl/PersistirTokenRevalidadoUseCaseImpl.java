package danieldjgomes.larica.app.usecase.token.usecase.impl;

import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.usecase.token.response.TokenResponse;
import danieldjgomes.larica.app.usecase.token.usecase.MontarTokenJWTUseCase;
import danieldjgomes.larica.app.usecase.token.usecase.PersistirTokenRevalidadoUseCase;
import danieldjgomes.larica.infrastructure.TokenRevalidado;
import danieldjgomes.larica.infrastructure.TokenRevalidadoRepository;
import danieldjgomes.larica.infrastructure.config.HMACEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
@Component
@RequiredArgsConstructor
public class PersistirTokenRevalidadoUseCaseImpl implements PersistirTokenRevalidadoUseCase {

    private final MontarTokenJWTUseCase montarTokenJWTUseCase;
    private final TokenRevalidadoRepository tokenRevalidadoRepository;
    private final HMACEncoder hmacEncoder;

    @Override
    public void persistir(UsuarioEntity usuario, TokenResponse token) {
        TokenRevalidado refreshTokenPersistido = new TokenRevalidado();
        refreshTokenPersistido.setToken(hmacEncoder.encode(token.getRefreshToken()));
        refreshTokenPersistido.setExpiracao(LocalDateTime.from(montarTokenJWTUseCase.getExpirationDateRefreshToken().atZone(ZoneId.of("America/Sao_Paulo"))));
        tokenRevalidadoRepository.save(refreshTokenPersistido);

    }
}
