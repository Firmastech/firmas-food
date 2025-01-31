package danieldjgomes.larica.app.usecase.token;

import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.adapter.database.pedidos.repository.UsuarioRepository;
import danieldjgomes.larica.app.usecase.token.request.LoginUsuarioRequest;
import danieldjgomes.larica.app.usecase.token.request.RevalidarTokenRequest;
import danieldjgomes.larica.app.usecase.token.response.TokenResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GerarTokenUsuarioUseCaseTest {

    @InjectMocks
    GerarTokenUsuarioUseCaseImpl gerarTokenUsuarioUseCase;

    @Mock
    ValidarRestauranteNoTokenUseCase validarRestauranteNoTokenUseCase;
    @Mock
    ValidarEmailNoTokenUseCase validarEmailNoTokenUseCase;
    @Mock
    MontarTokenJWTUseCase montarTokenJWTUseCase;
    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    AuthenticationManager authenticationManager;


    @Test
    public void deveProcessar() {
        LoginUsuarioRequest loginUsuarioRequest = new LoginUsuarioRequest();
        loginUsuarioRequest.setRestaurante("abc");
        loginUsuarioRequest.setEmail("abc@email.com");
        loginUsuarioRequest.setSenha("minhaSenha");


        UsuarioEntity usuarioMock = mock(UsuarioEntity.class);
        Authentication authenticationMock = mock(Authentication.class);

        when(authenticationMock.getPrincipal()).thenReturn(usuarioMock);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authenticationMock);
        when(montarTokenJWTUseCase.montar(any())).thenReturn(new TokenResponse("exemploAccessToken","exemploRefreshToken"));

        TokenResponse response = gerarTokenUsuarioUseCase.processar(loginUsuarioRequest);
        Assertions.assertEquals("exemploAccessToken", response.getAccessToken());
        Assertions.assertEquals("exemploRefreshToken", response.getRefreshToken());

    }
}