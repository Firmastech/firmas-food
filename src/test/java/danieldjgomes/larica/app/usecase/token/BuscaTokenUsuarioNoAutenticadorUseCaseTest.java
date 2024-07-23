package danieldjgomes.larica.app.usecase.token;

import danieldjgomes.larica.app.usecase.token.request.LoginUsuarioRequest;
import danieldjgomes.larica.app.usecase.usuario.request.external.LoginUsuarioKeycloakModelDTO;
import danieldjgomes.larica.app.usecase.usuario.request.external.TokenAutenticacaoKeycloakModelResponseDTO;
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
class BuscaTokenUsuarioNoAutenticadorUseCaseTest {


    @InjectMocks
    private BuscaTokenUsuarioNoAutenticadorUseCase buscaTokenUsuarioNoAutenticadorUseCase;

    @Mock
    private KeycloakUserClient keycloakUserClient;

    @Spy
    private TokenMapperImpl tokenMapper;

    TokenMockBuilder tokenMockBuilder = new TokenMockBuilder();

    @Test
    public void deveProcessar() {
        when(keycloakUserClient.buscarTokenUsuario(any(LoginUsuarioKeycloakModelDTO.class))).thenReturn(tokenMockBuilder.gerarMockTokenResponseKeycloak());
        PegarTokenUsuarioProcessModel processo = buscaTokenUsuarioNoAutenticadorUseCase.processar(tokenMockBuilder.gerarProcessModel());
        Assertions.assertEquals("6f19009d-c91b-4106-8150-66f9bdd1a3ed", processo.getUsuarioId());
        Assertions.assertEquals("exemploAccessToken", processo.getResultante().getAccessToken().getValor());
        Assertions.assertEquals(3600, processo.getResultante().getAccessToken().getExpiraEm());
        Assertions.assertEquals("exemploRefreshToken", processo.getResultante().getRefreshToken().getValor());
        Assertions.assertEquals(1800, processo.getResultante().getRefreshToken().getExpiraEm());

    }



}