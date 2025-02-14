package danieldjgomes.larica.app.usecase.token.usecase;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.auth0.jwt.exceptions.JWTCreationException;
import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;
import danieldjgomes.larica.app.usecase.token.exceptions.ErroAoMontarTokenException;
import danieldjgomes.larica.app.usecase.token.response.TokenResponse;
import danieldjgomes.larica.app.usecase.token.usecase.impl.MontarTokenJWTUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class MontarTokenJWTUseCaseTest {

  @InjectMocks
  private MontarTokenJWTUseCaseImpl montarTokenJWTUseCase;

  private final String secret = "chave-secreta";
  private final String issuer = "meu-app";
  private final String restauranteClaim = "restaurante";
  private final long tempoDeExpiracaoToken = 60;
  private final long tempoDeExpiracaoRefreshToken = 120;

  @BeforeEach
  void setUp() {
    ReflectionTestUtils.setField(montarTokenJWTUseCase, "secret", secret);
    ReflectionTestUtils.setField(montarTokenJWTUseCase, "issuer", issuer);
    ReflectionTestUtils.setField(montarTokenJWTUseCase, "restaurante", restauranteClaim);
    ReflectionTestUtils.setField(montarTokenJWTUseCase, "tempoDeExpiracaoToken", tempoDeExpiracaoToken);
    ReflectionTestUtils.setField(montarTokenJWTUseCase, "tempoDeExpiracaoRefreshToken", tempoDeExpiracaoRefreshToken);
  }

  @Test
  void deveGerarTokenQuandoUsuarioValido() {
    UsuarioEntity usuario = mock(UsuarioEntity.class);
    when(usuario.getEmail()).thenReturn("usuario@email.com");
    when(usuario.getRestaurante()).thenReturn(new RestauranteEntity()); // Simula o ID do restaurante

    TokenResponse response = montarTokenJWTUseCase.montar(usuario);

    assertNotNull(response);
    assertNotNull(response.getAccessToken());
    assertNotNull(response.getRefreshToken());
    assertFalse(response.getAccessToken().isEmpty());
    assertFalse(response.getRefreshToken().isEmpty());
  }

  @Test
  void deveLancarExcecaoQuandoHouverErroAoCriarToken() {
    UsuarioEntity usuario = mock(UsuarioEntity.class);
    when(usuario.getEmail()).thenReturn(null);
    when(usuario.getRestaurante()).thenThrow(new JWTCreationException("Erro ao criar token", null));
    assertThrows(ErroAoMontarTokenException.class, () -> montarTokenJWTUseCase.montar(usuario));
  }
}