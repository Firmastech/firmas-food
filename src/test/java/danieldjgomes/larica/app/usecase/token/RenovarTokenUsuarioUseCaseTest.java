package danieldjgomes.larica.app.usecase.token;

import danieldjgomes.larica.app.usecase.token.request.RevalidarTokenRequest;
import danieldjgomes.larica.app.usecase.token.response.TokenResponse;
import danieldjgomes.larica.app.usecase.usuario.request.external.RenovarTokenUsuarioKeycloakModelDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RenovarTokenUsuarioUseCaseTest {

    @InjectMocks
    RenovarTokenUsuarioUseCaseImpl renovarTokenUsuarioUseCase;

    @Mock
    private KeycloakUserClient keycloakUserClient;

    @Spy
    private TokenMapperImpl tokenMapper;

    TokenMockBuilder tokenMockBuilder = new TokenMockBuilder();

    @Test
    public void deveProcessar() {
        when(keycloakUserClient.revalidarTokenUsuario(any(RenovarTokenUsuarioKeycloakModelDTO.class))).thenReturn(tokenMockBuilder.gerarMockTokenResponseKeycloak());
        RevalidarTokenRequest revalidarTokenRequest = new RevalidarTokenRequest();
        revalidarTokenRequest.setToken("token-valido");

        TokenResponse response = renovarTokenUsuarioUseCase.processar(revalidarTokenRequest);
        Assertions.assertEquals("exemploAccessToken", response.getAccessToken().getValor());
        Assertions.assertEquals(3600, response.getAccessToken().getExpiraEm());
        Assertions.assertEquals("exemploRefreshToken", response.getRefreshToken().getValor());
        Assertions.assertEquals(1800, response.getRefreshToken().getExpiraEm());

    }


}