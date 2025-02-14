package danieldjgomes.larica.app.usecase.token.usecase;

import static org.junit.jupiter.api.Assertions.*;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import danieldjgomes.larica.app.usecase.token.exceptions.RestauranteNoTokenInvalidoException;
import danieldjgomes.larica.app.usecase.token.usecase.impl.ValidarRestauranteNoTokenUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class ValidarRestauranteNoTokenUseCaseTest {

  @InjectMocks
  private ValidarRestauranteNoTokenUseCaseImpl validarRestauranteNoTokenUseCase;

  private final String secret = "chave-secreta";
  private final String issuer = "meu-app";
  private final String restauranteClaim = "restaurante";

  @BeforeEach
  void setUp() {
    // Define os valores dos atributos anotados com @Value
    ReflectionTestUtils.setField(validarRestauranteNoTokenUseCase, "secret", secret);
    ReflectionTestUtils.setField(validarRestauranteNoTokenUseCase, "issuer", issuer);
    ReflectionTestUtils.setField(validarRestauranteNoTokenUseCase, "restaurante", restauranteClaim);
  }

  @Test
  void deveRetornarRestauranteQuandoTokenForValido() {
    // Criação do token válido
    String restauranteEsperado = "restaurante_exemplo";
    String token = JWT.create()
        .withIssuer(issuer)
        .withClaim(restauranteClaim, restauranteEsperado)
        .sign(Algorithm.HMAC256(secret));

    // Execução
    String resultado = validarRestauranteNoTokenUseCase.validar(token);

    // Verificação
    assertEquals(restauranteEsperado, resultado);
  }

  @Test
  void deveLancarExcecaoQuandoTokenForInvalido() {
    // Token inválido
    String tokenInvalido = "tokenInvalido";

    // Execução e verificação
    assertThrows(RestauranteNoTokenInvalidoException.class, () -> validarRestauranteNoTokenUseCase.validar(tokenInvalido));
  }
}