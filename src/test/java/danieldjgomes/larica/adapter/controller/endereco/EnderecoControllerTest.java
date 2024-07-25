package danieldjgomes.larica.adapter.controller.endereco;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import danieldjgomes.larica.core.endereco.entity.Endereco;
import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.core.restaurante.entity.enums.StatusFuncionamento;
import danieldjgomes.larica.core.usecases.endereco.AtualizarEnderecoUseCase;
import danieldjgomes.larica.core.usecases.endereco.ConsultarEnderecoPorIdUseCase;
import danieldjgomes.larica.core.usecases.endereco.InativarEnderecoUseCase;
import danieldjgomes.larica.infrastructure.mapper.DTOMapper;
import danieldjgomes.larica.usecase.endereco.request.AtualizarEnderecoRequest;
import danieldjgomes.larica.usecase.restaurante.request.AtualizarRestauranteRequest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URISyntaxException;
import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class EnderecoControllerTest {

    @Mock
    private ConsultarEnderecoPorIdUseCase consultarEnderecoPorIdUseCase;

    @Mock
    private AtualizarEnderecoUseCase atualizarEnderecoUseCase;

    @Mock
    private InativarEnderecoUseCase inativarEnderecoUseCase;

    @Mock
    private DTOMapper mapper;

    @InjectMocks
    private EnderecoController enderecoController;

    private MockMvc mockMvc;

    private AtualizarEnderecoRequest dtoAtualizarBuilder(){
        AtualizarEnderecoRequest dto = new AtualizarEnderecoRequest();
        dto.setId(UUID.randomUUID().toString());
        dto.setRua("Rua Renan no Teste");
        dto.setNumero("1234B");
        dto.setCep("74185-321");
        dto.setCidade("São Paulo");
        dto.setUf("SP");
        dto.setPontoReferencia("Proximo ao trintao");
        return dto;
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(enderecoController).build();
    }

    @Test
    void deveRetornarStatus200AoRealizarChamadaAoUseCaseConsultarEnderecoById() throws Exception {
        String id = UUID.randomUUID().toString();

        mockMvc.perform(get("/api/endereco/"+ id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(consultarEnderecoPorIdUseCase, times(1)).consultar(id);
    }

    @Test
    void deveRetornarStatus204AoRealizarChamadaAoUseCaseAtualizarEndereco() throws Exception {
        doReturn(new Endereco()).when(mapper).toEndereco((AtualizarEnderecoRequest) any());

        AtualizarEnderecoRequest dto = dtoAtualizarBuilder();
        Endereco endereco = mapper.toEndereco(dto);
        ObjectMapper objectMapper = new ObjectMapper();
        String dtoJson = objectMapper.writeValueAsString(dto);


        mockMvc.perform(put("/api/endereco")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoJson))
                .andExpect(status().isNoContent());

        verify(atualizarEnderecoUseCase, times(1)).update(endereco);
    }

    @Test
    void deveRetornarStatus204AoRealizarChamadaAoUseCaseInativarEndereco() throws Exception {
        String id = UUID.randomUUID().toString();

        mockMvc.perform(delete("/api/endereco/"+id))
                .andExpect(status().isNoContent());

        verify(inativarEnderecoUseCase, times(1)).inativar(id);
    }

    @Test
    void deveRetornarStatus400AoRealizarChamadaAoUseCaseConsultarEnderecoById() throws Exception {
        mockMvc.perform(get("/api/endereco/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void deveRetornarStatus400AoRealizarChamadaAoUseCaseAtualizarEndereco() throws Exception {
        doReturn(new Endereco()).when(mapper).toEndereco((AtualizarEnderecoRequest) any());

        AtualizarEnderecoRequest dto = dtoAtualizarBuilder();
        dto.setId(null);
        Endereco endereco = mapper.toEndereco(dto);
        ObjectMapper objectMapper = new ObjectMapper();
        String dtoJson = objectMapper.writeValueAsString(dto);


        mockMvc.perform(put("/api/endereco")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornarStatus204AoRealizarChamadaAoUseCaseinativarEndereco() throws Exception {
        String id = UUID.randomUUID().toString();

        mockMvc.perform(delete("/api/endereco/"+id))
                .andExpect(status().isNoContent());
    }
}