package danieldjgomes.larica.adapter.controller.restaurante;

import com.fasterxml.jackson.databind.ObjectMapper;
import danieldjgomes.larica.app.adapter.controller.restaurante.RestauranteController;
import danieldjgomes.larica.app.adapter.mapper.RestauranteMapper;
import danieldjgomes.larica.app.usecase.endereco.response.Endereco;
import danieldjgomes.larica.app.usecase.restaurante.response.Restaurante;
import danieldjgomes.larica.app.usecase.restaurante.enums.StatusFuncionamento;
import danieldjgomes.larica.app.usecase.restaurante.port.AtualizarRestauranteUseCase;
import danieldjgomes.larica.app.usecase.restaurante.port.ConsultarRestauranteUseCase;
import danieldjgomes.larica.app.usecase.restaurante.port.RegistrarRestauranteUseCase;
import danieldjgomes.larica.app.usecase.restaurante.port.InativarRestauranteUseCase;
import danieldjgomes.larica.app.usecase.restaurante.request.AtualizarRestauranteRequest;
import danieldjgomes.larica.app.usecase.endereco.request.CadastrarEnderecoRequest;
import danieldjgomes.larica.app.usecase.restaurante.request.CriarRestauranteRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RestauranteControllerTest {

    @Mock
    private RegistrarRestauranteUseCase registrarRestauranteInterador;

    @Mock
    private ConsultarRestauranteUseCase consultarRestauranteUseCase;

    @Mock
    private AtualizarRestauranteUseCase atualizarRestauranteUseCase;

    @Mock
    private InativarRestauranteUseCase inativarRestauranteUseCase;

    @Mock
    private RestauranteMapper mapper;

    @InjectMocks
    private RestauranteController restauranteController;

    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(restauranteController).build();
    }

    private CriarRestauranteRequest dtoBuilder(){
        CriarRestauranteRequest dto = new CriarRestauranteRequest();
        dto.setNome("Restaurante Teste");
        dto.setTempoEstimadoDeEntrega(60);
        dto.setStatusFuncionamento(StatusFuncionamento.INATIVO);
        dto.setEndereco(new CadastrarEnderecoRequest());
        return dto;
    }

    private AtualizarRestauranteRequest dtoAtualizarBuilder(){
        AtualizarRestauranteRequest dto = new AtualizarRestauranteRequest();
        dto.setId(UUID.randomUUID().toString());
        dto.setNome("Restaurante Atualizado");
        dto.setTempoEstimadoDeEntrega(120);
        dto.setStatusFuncionamento(StatusFuncionamento.ABERTO);
        return dto;
    }

    private Restaurante restauranteBuilder(){
        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Restaurante Teste");
        restaurante.setTempoEstimadoDeEntrega(60);
        restaurante.setStatusFuncionamento(StatusFuncionamento.INATIVO);
        restaurante.setEndereco(new Endereco());
        return restaurante;
    }

    @Test
    void deveRetornarStatus201RealizarChamadaAoUseCaseRegistrarRestaurante() throws Exception {
        CriarRestauranteRequest dto = dtoBuilder();
        Restaurante restaurante = restauranteBuilder();
        when(mapper.toRestaurante(dto)).thenReturn(restaurante);

        ObjectMapper objectMapper = new ObjectMapper();
        String dtoJson = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/api/restaurantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoJson))
                .andExpect(status().isCreated());

        verify(registrarRestauranteInterador, times(1))
                .registrarRestaurante(restaurante);
    }

    @Test
    void deveRetornarStatus200RealizarChamadaAoUseCaseConsultarRestaurante() throws Exception {
        String id = UUID.randomUUID().toString();

        mockMvc.perform(get("/api/restaurantes/"+ id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(consultarRestauranteUseCase, times(1)).consultar(id);
    }

    @Test
    void deveRetornarStatus204RealizarChamadaAoUseCaseInativarRestaurante() throws Exception {
        doReturn(new Restaurante()).when(mapper).toRestaurante((AtualizarRestauranteRequest) any());

        AtualizarRestauranteRequest dto = dtoAtualizarBuilder();
        Restaurante restaurante = mapper.toRestaurante(dto);

        ObjectMapper objectMapper = new ObjectMapper();
        String dtoJson = objectMapper.writeValueAsString(dto);

        mockMvc.perform(put("/api/restaurantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoJson))
                .andExpect(status().isNoContent());

        verify(atualizarRestauranteUseCase, times(1)).update(restaurante);
    }

    @Test
    void deveRetornarStatus204RelizarChamadaAoUseCaseComIdPassado() throws Exception {
        String id = UUID.randomUUID().toString();

        mockMvc.perform(delete("/api/restaurantes/"+id))
                .andExpect(status().isNoContent());

        verify(inativarRestauranteUseCase, times(1)).inativarRestaurante(id);
    }

    @Test
    void deveRetornarStatus400QuandoPassarAtributosNulos() throws Exception {
        CriarRestauranteRequest dto = dtoBuilder();
        dto.setNome(null);
        dto.setTempoEstimadoDeEntrega(null);
        dto.setStatusFuncionamento(null);

        ObjectMapper objectMapper = new ObjectMapper();
        String dtoJson = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/api/restaurantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoJson))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void deveRetornarStatus400QuandoPassarIdNuloUseCaseAtualizarRestaurante() throws Exception {
        doReturn(new Restaurante()).when(mapper).toRestaurante((AtualizarRestauranteRequest) any());

        AtualizarRestauranteRequest dto = dtoAtualizarBuilder();
        dto.setId(null);
        Restaurante restaurante = mapper.toRestaurante(dto);

        ObjectMapper objectMapper = new ObjectMapper();
        String dtoJson = objectMapper.writeValueAsString(dto);

        mockMvc.perform(put("/api/restaurantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornar405QuandoNaoInformarIDnoPath() throws Exception {
        mockMvc.perform(get("/api/restaurantes/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());

    }

    @Test
    void deveRetornar405QuandoNaoInformarIDnoPathDeDelete() throws Exception {
        mockMvc.perform(delete("/api/restaurantes"))
                .andExpect(status().isMethodNotAllowed());

    }
}