package danieldjgomes.larica.infrastructure.token;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.gson.Gson;
import danieldjgomes.larica.infrastructure.keycloak.KeycloakService;
import danieldjgomes.larica.infrastructure.token.model.LoginResponse;
import danieldjgomes.larica.infrastructure.token.model.LoginUsuarioRequest;
import danieldjgomes.larica.infrastructure.token.model.RevalidarTokenRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TokenControllerTest {

    private MockMvc mockMvc;

    @Mock
    private KeycloakService keycloakService;

    @InjectMocks
    private TokenController tokenController;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(tokenController).build();
    }

    private LoginResponse gerarLoginResponse() {

        LoginResponse.Token accessToken = new LoginResponse.Token();
        accessToken.setValor("accessTokenValue");
        accessToken.setExpiraEm(3600L);

        LoginResponse.Token refreshToken = new LoginResponse.Token();
        refreshToken.setValor("refreshTokenValue");
        refreshToken.setExpiraEm(7200L);

        LoginResponse validLoginResponse = new LoginResponse();
        validLoginResponse.setAccessToken(accessToken);
        validLoginResponse.setRefreshToken(refreshToken);
        return validLoginResponse;
    }

    private static LoginUsuarioRequest gerarRequestLoginValido() {
        LoginUsuarioRequest validLoginRequest;
        validLoginRequest = new LoginUsuarioRequest();
        validLoginRequest.setUsuarioId("validUser");
        validLoginRequest.setSenha("validPassword");

        return validLoginRequest;
    }

    @Test
    void deveRetornarTokensEStatusOkComCredenciaisValidas() throws Exception {
        when(keycloakService.logarUsuario(anyString(), anyString())).thenReturn(gerarLoginResponse());

        ObjectMapper mapper = new ObjectMapper();
        MvcResult result = mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(gerarRequestLoginValido())))
                .andExpect(status().isOk()).andReturn();

        String responseBody = result.getResponse().getContentAsString();
        LoginResponse response = new Gson().fromJson(responseBody, LoginResponse.class);

        assertEquals(response.getAccessToken().getValor(), "accessTokenValue");
        assertEquals(response.getAccessToken().getExpiraEm(), 3600L);
        assertEquals(response.getRefreshToken().getValor(), "refreshTokenValue");
        assertEquals(response.getRefreshToken().getExpiraEm(), 7200L);
    }

    @Test
    void deveRetornarBadRequestPorCredenciaisInvalidasParaRequesicaodeLogin() throws Exception {
        LoginUsuarioRequest invalidLoginRequest = new LoginUsuarioRequest();
        invalidLoginRequest.setUsuarioId("");
        invalidLoginRequest.setSenha("validPassword");

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/login")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(invalidLoginRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRevalidarTokenEretornarNovosTokensEStatusOk() throws Exception {
        when(keycloakService.revalidarToken(any(RevalidarTokenRequest.class))).thenReturn(gerarLoginResponse());

        RevalidarTokenRequest requestValido = new RevalidarTokenRequest();
        requestValido.setToken("tokenValido");

        ObjectMapper mapper = new ObjectMapper();

        MvcResult mvcResult = mockMvc.perform(post("/refresh")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(requestValido)))
                .andExpect(status().isOk()).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        LoginResponse response = new Gson().fromJson(responseBody, LoginResponse.class);

        assertEquals(response.getAccessToken().getValor(), "accessTokenValue");
        assertEquals(response.getAccessToken().getExpiraEm(), 3600L);
        assertEquals(response.getRefreshToken().getValor(), "refreshTokenValue");
        assertEquals(response.getRefreshToken().getExpiraEm(), 7200L);
    }


    @Test
    void deveRetornarBadRequestPorCredenciaisInvalidasParaRequisicaoDeRevalidacao() throws Exception {
        RevalidarTokenRequest invalidRevalidarTokenRequest = new RevalidarTokenRequest();
        invalidRevalidarTokenRequest.setToken("");

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/refresh")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(invalidRevalidarTokenRequest)))
                .andExpect(status().isBadRequest());
    }
}
