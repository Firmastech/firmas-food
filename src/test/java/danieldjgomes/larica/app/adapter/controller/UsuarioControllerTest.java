package danieldjgomes.larica.app.adapter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import danieldjgomes.larica.app.adapter.controller.exceptionHandler.CommonExceptionHandler;
import danieldjgomes.larica.app.adapter.controller.exceptionHandler.UsuarioControllerExceptionHandler;
import danieldjgomes.larica.app.usecase.usuario.CriarClienteUseCase;
import danieldjgomes.larica.app.usecase.usuario.request.CriarUsuarioRequestDTO;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@Import(UsuarioControllerExceptionHandler.class)
class UsuarioControllerTest {


    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Mock
    private CriarClienteUseCase criarClienteUseCase;

    @InjectMocks
    private UsuarioController usuarioController;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController)
                .setControllerAdvice(new UsuarioControllerExceptionHandler(new CommonExceptionHandler()))
                .build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

    }

    @Test
    void deveRetornarCreatedQuandoCriarUsuarioComRequisicaoValida() throws Exception {

        CriarUsuarioRequestDTO request = new CriarUsuarioRequestDTO();
        request.setRestaurante("restauranteValido");
        request.setNome("Joao");
        request.setSobrenome("do test");
        request.setEmail("jao@test.com");
        request.setSenha("senhaMuitoSecreta");

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        verify(criarClienteUseCase, times(1)).processar(any(CriarUsuarioRequestDTO.class));
    }

    @Test
    void deveRetornarBadRequestQuandoCriarUsuarioComRequisicaoInvalida() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest()).andReturn();

        String controllerResponse = mvcResult.getResponse().getContentAsString();
        ErrorResponse tokenResponse = objectMapper.readValue(controllerResponse, ErrorResponse.class);
        assertEquals(5, tokenResponse.getMensagens().size());
        assertNotNull(tokenResponse.getTimestamp());
    }

}