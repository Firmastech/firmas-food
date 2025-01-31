package danieldjgomes.larica.app.usecase.token;

import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.adapter.database.pedidos.repository.UsuarioRepository;
import danieldjgomes.larica.app.usecase.token.request.RevalidarTokenRequest;
import danieldjgomes.larica.app.usecase.token.response.TokenResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RenovarTokenUsuarioUseCaseTest {

    @InjectMocks
    RenovarTokenUsuarioUseCaseImpl renovarTokenUsuarioUseCase;

    @Mock
    ValidarRestauranteNoTokenUseCase validarRestauranteNoTokenUseCase;

    @Mock
    ValidarEmailNoTokenUseCase validarEmailNoTokenUseCase;

    @Mock
    MontarTokenJWTUseCase montarTokenJWTUseCase;

    @Mock
    UsuarioRepository usuarioRepository;

    TokenMockBuilder tokenMockBuilder = new TokenMockBuilder();

    @Test
    public void deveProcessar() {
        RevalidarTokenRequest revalidarTokenRequest = new RevalidarTokenRequest();
        revalidarTokenRequest.setToken("token-valido");

        when(usuarioRepository.findAllByRestauranteIdAndEmailAndAtivoTrue(any(), any())).thenReturn(Optional.of(new UsuarioEntity()));
        when(montarTokenJWTUseCase.montar(any())).thenReturn(new TokenResponse("exemploAccessToken", "exemploRefreshToken"));

        TokenResponse response = renovarTokenUsuarioUseCase.processar(revalidarTokenRequest);
        Assertions.assertEquals("exemploAccessToken", response.getAccessToken());
        Assertions.assertEquals("exemploRefreshToken", response.getRefreshToken());

    }


}