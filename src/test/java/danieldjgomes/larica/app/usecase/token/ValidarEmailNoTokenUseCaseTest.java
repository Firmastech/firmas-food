package danieldjgomes.larica.app.usecase.token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import danieldjgomes.larica.app.usecase.token.exceptions.EmailNoTokenInvalidoException;
import danieldjgomes.larica.app.usecase.token.usecase.impl.ValidarEmailNoTokenUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class ValidarEmailNoTokenUseCaseTest {

  @InjectMocks
  private ValidarEmailNoTokenUseCaseImpl validarEmailNoTokenUseCase;

  private final String secret = "chave-secreta";
  private final String issuer = "meu-app";

  @BeforeEach
  void setUp() {
    ReflectionTestUtils.setField(validarEmailNoTokenUseCase, "secret", secret);
    ReflectionTestUtils.setField(validarEmailNoTokenUseCase, "issuer", issuer);
  }

  @Test
  void deveRetornarEmailQuandoTokenForValido() {
    String emailEsperado = "usuario@email.com";
    String token = JWT.create().withIssuer(issuer).withSubject(emailEsperado)
        .sign(Algorithm.HMAC256(secret));

    String resultado = validarEmailNoTokenUseCase.validar(token);

    assertEquals(emailEsperado, resultado);
  }

  @Test
  void deveLancarExcecaoQuandoTokenForInvalido() {
    String tokenInvalido = "tokenInvalido";

    assertThrows(EmailNoTokenInvalidoException.class,
        () -> validarEmailNoTokenUseCase.validar(tokenInvalido));
  }

}