package danieldjgomes.larica.app.usecase.token.usecase.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import danieldjgomes.larica.app.usecase.token.exceptions.EmailNoTokenInvalidoException;
import danieldjgomes.larica.app.usecase.token.usecase.ValidarEmailNoTokenUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ValidarEmailNoTokenUseCaseImpl implements ValidarEmailNoTokenUseCase {

    @Value(value = "${api.security.token.secret}")
    private String secret;
    @Value(value = "${api.security.token.issuer}")
    private String issuer;

    @Override
    public String validar(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException e) {
            throw new EmailNoTokenInvalidoException(e);
        }
    }
}
