//package danieldjgomes.larica.infrastructure.token;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.nimbusds.jose.shaded.gson.Gson;
//import danieldjgomes.larica.adapter.controller.TokenController;
//import danieldjgomes.larica.infrastructure.keycloak.dto.LoginUsuarioKeycloakModelDTO;
//import danieldjgomes.larica.infrastructure.keycloak.dto.TokenAutenticacaoKeycloakModelResponseDTO;
//import danieldjgomes.larica.usecase.port.token.BuscarTokenUsuarioUseCaseImpl;
//import danieldjgomes.larica.usecase.port.token.RenovarTokenUsuarioUseCaseImpl;
//import danieldjgomes.larica.usecase.port.token.request.LoginUsuarioRequest;
//import danieldjgomes.larica.usecase.port.token.request.RevalidarTokenRequest;
//import danieldjgomes.larica.usecase.port.token.response.TokenResponse;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//public class TokenControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private KeycloakUserClient keycloakUserClient;
//
//    @Mock
//    private TokenMapper tokenMapper;
//
//    private BuscarTokenUsuarioUseCaseImpl buscarTokenUsuarioUseCase;
//    private RenovarTokenUsuarioUseCaseImpl renovarTokenUsuarioUseCase;
//    private TokenController tokenController;
//
//    @BeforeEach
//    void setUp() {
//        buscarTokenUsuarioUseCase = new BuscarTokenUsuarioUseCaseImpl(keycloakUserClient, tokenMapper);
//        renovarTokenUsuarioUseCase = new RenovarTokenUsuarioUseCaseImpl(keycloakUserClient, tokenMapper);
//        tokenController = new TokenController(buscarTokenUsuarioUseCase, renovarTokenUsuarioUseCase);
//        mockMvc = MockMvcBuilders.standaloneSetup(tokenController).build();
//    }
//
//    private TokenAutenticacaoKeycloakModelResponseDTO gerarMockTokenResponseKeycloak() {
//
//        TokenAutenticacaoKeycloakModelResponseDTO tokenDTO = new TokenAutenticacaoKeycloakModelResponseDTO();
//
//        tokenDTO.setAccessToken("exemploAccessToken");
//        tokenDTO.setExpiresIn(3600);
//        tokenDTO.setRefreshExpiresIn(1800);
//        tokenDTO.setRefreshToken("exemploRefreshToken");
//        tokenDTO.setTokenType("bearer");
//        tokenDTO.setNotBeforePolicy(0);
//        tokenDTO.setSessionState("exemploSessionState");
//        tokenDTO.setScope("openid email profile");
//        return tokenDTO;
//    }
//
//    private static LoginUsuarioRequest gerarRequestLoginValido() {
//        LoginUsuarioRequest validLoginRequest;
//        validLoginRequest = new LoginUsuarioRequest();
//        validLoginRequest.setUsuarioId("validUser");
//        validLoginRequest.setSenha("validPassword");
//
//        return validLoginRequest;
//    }
//
//    @Test
//    void deveRetornarTokensEStatusOkComCredenciaisValidas() throws Exception {
//        when(keycloakUserClient.buscarTokenUsuario(any(LoginUsuarioKeycloakModelDTO.class))).thenReturn(gerarMockTokenResponseKeycloak());
//
//        ObjectMapper mapper = new ObjectMapper();
//        MvcResult result = mockMvc.perform(post("/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(gerarRequestLoginValido())))
//                .andExpect(status().isOk()).andReturn();
//
//        String responseBody = result.getResponse().getContentAsString();
//        TokenResponse response = new Gson().fromJson(responseBody, TokenResponse.class);
//
//        assertEquals(response.getAccessToken().getValor(), "exemploAccessToken");
//        assertEquals(response.getAccessToken().getExpiraEm(), 3600);
//        assertEquals(response.getRefreshToken().getValor(), "exemploRefreshToken");
//        assertEquals(response.getRefreshToken().getExpiraEm(), 7200);
//    }
//
////    @Test
////    void deveRetornarBadRequestPorCredenciaisInvalidasParaRequesicaodeLogin() throws Exception {
////        LoginUsuarioRequest invalidLoginRequest = new LoginUsuarioRequest();
////        invalidLoginRequest.setUsuarioId("");
////        invalidLoginRequest.setSenha("validPassword");
////
////        ObjectMapper mapper = new ObjectMapper();
////
////        mockMvc.perform(post("/login")
////                        .contentType("application/json")
////                        .content(mapper.writeValueAsString(invalidLoginRequest)))
////                .andExpect(status().isBadRequest());
////    }
////
////    @Test
////    void deveRevalidarTokenEretornarNovosTokensEStatusOk() throws Exception {
////        when(keycloakUserClient.buscarTokenUsuario(any(LoginUsuarioKeycloakModelDTO.class))).thenReturn(gerarMockTokenResponseKeycloak());
////
////        RevalidarTokenRequest requestValido = new RevalidarTokenRequest();
////        requestValido.setToken("tokenValido");
////
////        ObjectMapper mapper = new ObjectMapper();
////
////        MvcResult mvcResult = mockMvc.perform(post("/refresh")
////                        .contentType("application/json")
////                        .content(mapper.writeValueAsString(requestValido)))
////                .andExpect(status().isOk()).andReturn();
////
////        String responseBody = mvcResult.getResponse().getContentAsString();
////        TokenResponse response = new Gson().fromJson(responseBody, TokenResponse.class);
////
////        assertEquals(response.getAccessToken().getValor(), "accessTokenValue");
////        assertEquals(response.getAccessToken().getExpiraEm(), 3600L);
////        assertEquals(response.getRefreshToken().getValor(), "refreshTokenValue");
////        assertEquals(response.getRefreshToken().getExpiraEm(), 7200L);
////    }
////
////
////    @Test
////    void deveRetornarBadRequestPorCredenciaisInvalidasParaRequisicaoDeRevalidacao() throws Exception {
////        RevalidarTokenRequest invalidRevalidarTokenRequest = new RevalidarTokenRequest();
////        invalidRevalidarTokenRequest.setToken("");
////
////        ObjectMapper mapper = new ObjectMapper();
////
////        mockMvc.perform(post("/refresh")
////                        .contentType("application/json")
////                        .content(mapper.writeValueAsString(invalidRevalidarTokenRequest)))
////                .andExpect(status().isBadRequest());
////    }
//}
