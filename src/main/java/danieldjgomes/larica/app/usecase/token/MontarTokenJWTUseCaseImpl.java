package danieldjgomes.larica.app.usecase.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.usecase.token.exceptions.ErroAoMontarTokenException;
import danieldjgomes.larica.app.usecase.token.response.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
@Component
public class MontarTokenJWTUseCaseImpl implements MontarTokenJWTUseCase {

    @Value(value = "${api.security.token.expiration-time.token}")
    private long tempoDeExpiracaoToken;
    @Value(value = "${api.security.token.expiration-time.refresh-token}")
    private long tempoDeExpiracaoRefreshToken;
    @Value(value = "${api.security.token.secret}")
    private String secret;
    @Value(value = "${api.security.token.issuer}")
    private String issuer;
    @Value(value = "${api.security.token.restaurante-claim}")
    private String restaurante;

    @Override
    public TokenResponse montar(UsuarioEntity usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer(issuer)
                    .withSubject(usuario.getEmail())
                    .withClaim(restaurante, usuario.getRestaurante().getId())
                    .withExpiresAt(getExpirationDateToken())
                    .sign(algorithm);
            String refreshToken = JWT.create()
                    .withIssuer(issuer)
                    .withSubject(usuario.getEmail())
                    .withClaim(restaurante, usuario.getRestaurante().getId())
                    .withExpiresAt(getExpirationDateRefreshToken())
                    .sign(algorithm);
            return new TokenResponse(token, refreshToken);

        } catch (JWTCreationException e) {
            throw new ErroAoMontarTokenException(e);
        }
    }
    private Instant getExpirationDateToken() {
        return LocalDateTime.now().plusMinutes(tempoDeExpiracaoToken).toInstant(ZoneOffset.of("-03:00"));
    }

    private Instant getExpirationDateRefreshToken() {
        return LocalDateTime.now().plusMinutes(tempoDeExpiracaoRefreshToken).toInstant(ZoneOffset.of("-03:00"));
    }
}
