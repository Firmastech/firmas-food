package danieldjgomes.larica.app.adapter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import danieldjgomes.larica.app.adapter.controller.exceptionHandler.CommonExceptionHandler;
import danieldjgomes.larica.app.adapter.controller.exceptionHandler.TokenControllerExceptionHandler;
import danieldjgomes.larica.app.usecase.token.BuscarTokenUsuarioUseCase;
import danieldjgomes.larica.app.usecase.token.RenovarTokenUsuarioUseCase;
import danieldjgomes.larica.app.usecase.token.TokenMockBuilder;
import danieldjgomes.larica.app.usecase.token.request.LoginUsuarioRequest;
import danieldjgomes.larica.app.usecase.token.request.RevalidarTokenRequest;
import danieldjgomes.larica.app.usecase.token.response.TokenResponse;
import danieldjgomes.larica.infrastructure.expectionHandler.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@Import(TokenControllerExceptionHandler.class)
class TokenControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Mock
    private BuscarTokenUsuarioUseCase buscarTokenUsuarioUseCase;

    @Mock
    private RenovarTokenUsuarioUseCase renovarTokenUsuarioUseCase;

    @InjectMocks
    private TokenController tokenController;

    private final TokenMockBuilder tokenMockBuilder = new TokenMockBuilder();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(tokenController)
                .setControllerAdvice(new TokenControllerExceptionHandler(new CommonExceptionHandler())).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void deveRetornarTokenValidoQuandoLogarUsuarioComRequisicaoValida() throws Exception {

        LoginUsuarioRequest request = tokenMockBuilder.gerarRequestLoginValido();
        TokenResponse response = tokenMockBuilder.geraTokenResponseValido();
        when(buscarTokenUsuarioUseCase.processar(any(LoginUsuarioRequest.class))).thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk()).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        TokenResponse tokenResponse = objectMapper.readValue(responseBody, TokenResponse.class);

        assertEquals(tokenResponse.getAccessToken().getValor(), "accessTokenValue");
        assertEquals(tokenResponse.getAccessToken().getExpiraEm(), 3600L);
        assertEquals(tokenResponse.getRefreshToken().getValor(), "refreshTokenValue");
        assertEquals(tokenResponse.getRefreshToken().getExpiraEm(), 7200L);

        verify(buscarTokenUsuarioUseCase, times(1)).processar(any(LoginUsuarioRequest.class));
    }

    @Test
    void deveRetornarErroQuandoLogarUsuarioComRequestBodyVazio() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest()).andReturn();

        String controllerResponse = mvcResult.getResponse().getContentAsString();
        ErrorResponse tokenResponse = objectMapper.readValue(controllerResponse, ErrorResponse.class);
        assertEquals(3, tokenResponse.getMensagens().size());
        assertNotNull(tokenResponse.getTimestamp());
        verify(buscarTokenUsuarioUseCase, never()).processar(any(LoginUsuarioRequest.class));
    }

    @Test
    void deveRetornarTokenValidoQuandoRevalidarTokenComRequisicaoValida() throws Exception {

        RevalidarTokenRequest request = new RevalidarTokenRequest();
        request.setToken("tokenValido");
        TokenResponse response = tokenMockBuilder.geraTokenResponseValido();
        when(renovarTokenUsuarioUseCase.processar(request)).thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/refresh")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk()).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        TokenResponse tokenResponse = objectMapper.readValue(responseBody, TokenResponse.class);

        assertEquals(tokenResponse.getAccessToken().getValor(), "accessTokenValue");
        assertEquals(tokenResponse.getAccessToken().getExpiraEm(), 3600L);
        assertEquals(tokenResponse.getRefreshToken().getValor(), "refreshTokenValue");
        assertEquals(tokenResponse.getRefreshToken().getExpiraEm(), 7200L);

        verify(renovarTokenUsuarioUseCase, times(1)).processar(any(RevalidarTokenRequest.class));
    }

    @Test
    void deveRetornarErroQuandoRevalidarTokenComRequestBodyVazio() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/refresh")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest()).andReturn();

        String controllerResponse = mvcResult.getResponse().getContentAsString();
        ErrorResponse tokenResponse = objectMapper.readValue(controllerResponse, ErrorResponse.class);
        assertEquals(1, tokenResponse.getMensagens().size());
        assertNotNull(tokenResponse.getTimestamp());
        verify(renovarTokenUsuarioUseCase, never()).processar(any(RevalidarTokenRequest.class));
    }

}