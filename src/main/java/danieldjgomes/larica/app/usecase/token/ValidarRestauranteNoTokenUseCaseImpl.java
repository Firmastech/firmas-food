package danieldjgomes.larica.app.usecase.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import danieldjgomes.larica.app.usecase.token.exceptions.RestauranteNoTokenInvalidoException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ValidarRestauranteNoTokenUseCaseImpl implements ValidarRestauranteNoTokenUseCase {

    @Value(value = "${api.security.token.secret}")
    private String secret;
    @Value(value = "${api.security.token.issuer}")
    private String issuer;
    @Value(value = "${api.security.token.restaurante-claim}")
    private String restaurante;

    @Override
    public String validar(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getClaim(restaurante)
                    .asString();
        } catch (JWTVerificationException e) {
            throw new RestauranteNoTokenInvalidoException(e);
        }
    }
}
