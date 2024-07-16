package danieldjgomes.larica.infrastructure.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import danieldjgomes.larica.core.endereco.entity.Endereco;
import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.core.restaurante.entity.enums.StatusFuncionamento;
import danieldjgomes.larica.core.usecases.restaurante.AtualizarRestauranteUseCase;
import danieldjgomes.larica.core.usecases.restaurante.ConsultarRestauranteUseCase;
import danieldjgomes.larica.core.usecases.restaurante.RegistrarRestauranteUseCase;
import danieldjgomes.larica.core.usecases.restaurante.InativarRestauranteUseCase;
import danieldjgomes.larica.infrastructure.dto.restaurante.request.AtualizarRestauranteRequestDTO;
import danieldjgomes.larica.infrastructure.dto.restaurante.request.CriarRestauranteRequestDTO;
import danieldjgomes.larica.infrastructure.dto.restaurante.request.EnderecoDTO;
import danieldjgomes.larica.infrastructure.mapper.DTOMapper;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
    private DTOMapper mapper;

    @InjectMocks
    private RestauranteController restauranteController;

    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(restauranteController).build();
    }

    private CriarRestauranteRequestDTO dtoBuilder(){
        CriarRestauranteRequestDTO dto = new CriarRestauranteRequestDTO();
        dto.setNome("Restaurante Teste");
        dto.setTempoEstimadoDeEntrega(60);
        dto.setStatusFuncionamento(StatusFuncionamento.INATIVO);
        dto.setEndereco(new EnderecoDTO());
        return dto;
    }

    private AtualizarRestauranteRequestDTO dtoAtualizarBuilder(){
        AtualizarRestauranteRequestDTO dto = new AtualizarRestauranteRequestDTO();
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
    void deveRetornarStatus201_ErealizarChamadaAoUseCase_RegistrarRestaurante() throws Exception {
        CriarRestauranteRequestDTO dto = dtoBuilder();
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
    void deveRetornarStatus200_ErealizarChamadaAoUseCase_ConsultarRestaurante() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(get("/api/restaurantes/"+ id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(consultarRestauranteUseCase, times(1)).consultar(id);
    }

    @Test
    void deveRetornarStatus204_E_realizarChamadaAoUseCase_InativarRestaurante() throws Exception {
        doReturn(new Restaurante()).when(mapper).toRestaurante((AtualizarRestauranteRequestDTO) any());

        AtualizarRestauranteRequestDTO dto = dtoAtualizarBuilder();
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
    void deveRelizarChamadaAoUseCase_ComIdPassado_RetornarStatus204() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(delete("/api/restaurantes/"+id))
                .andExpect(status().isNoContent());

        verify(inativarRestauranteUseCase, times(1)).inativarRestaurante(id);
    }

    @Test
    void deveRetornarStatus400_quandoPassarAtributosNulos() throws Exception {
        CriarRestauranteRequestDTO dto = dtoBuilder();
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
    void deveRetornarStatus400_quandoPassarIdNulo_No_UseCaseAtualizarRestaurante() throws Exception {
        doReturn(new Restaurante()).when(mapper).toRestaurante((AtualizarRestauranteRequestDTO) any());

        AtualizarRestauranteRequestDTO dto = dtoAtualizarBuilder();
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
    void deveRetornar405_QuandoNaoInformarID_noPath_MetodoGET() throws Exception {
        mockMvc.perform(get("/api/restaurantes/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());

    }

    @Test
    void deveRetornar405_QuandoNaoInformarID_noPath_MetodoDELETE() throws Exception {
        mockMvc.perform(delete("/api/restaurantes"))
                .andExpect(status().isMethodNotAllowed());

    }
}