package danieldjgomes.larica.usecase.endereco;

import danieldjgomes.larica.app.adapter.database.endereco.model.EnderecoEntity;
import danieldjgomes.larica.app.adapter.mapper.EnderecoMapper;
import danieldjgomes.larica.app.usecase.endereco.ConsultarEnderecoPorIdUseCaseImpl;
import danieldjgomes.larica.app.usecase.endereco.response.Endereco;
import danieldjgomes.larica.app.ports.database.EnderecoPersist;
import danieldjgomes.larica.app.usecase.endereco.exceptions.EnderecoNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultarEnderecoPorIdUseCaseImplTest {

    @Mock
    private EnderecoPersist enderecoPersist;

    @Mock
    private EnderecoMapper mapper;

    @InjectMocks
    private ConsultarEnderecoPorIdUseCaseImpl consultarEnderecoPorIdUseCase;

    private EnderecoEntity modelExpected;
    private Endereco enderecoExpected;
    private String id = UUID.randomUUID().toString();

    @BeforeEach
    void setup(){
        modelExpected = new EnderecoEntity();
        modelExpected.setId(id);
        modelExpected.setRua("Rua Atualizada");
        modelExpected.setNumero("4321A");
        modelExpected.setCep("85241-321");
        modelExpected.setCidade("Rio de Janeiro");
        modelExpected.setUf("RJ");
        modelExpected.setPontoReferencia("Proximo ao cinquentão");

        enderecoExpected = new Endereco();
        enderecoExpected.setId(id);
        enderecoExpected.setRua("Rua Atualizada");
        enderecoExpected.setNumero("4321A");
        enderecoExpected.setCep("85241-321");
        enderecoExpected.setCidade("Rio de Janeiro");
        enderecoExpected.setUf("RJ");
        enderecoExpected.setPontoReferencia("Proximo ao cinquentão");
    }

    @Test
    void deveConsultarUmEnderecoCorretamentePorID() {
        when(mapper.toEndereco(modelExpected)).thenReturn(enderecoExpected);
        when(enderecoPersist.findById(id))
                .thenReturn(Optional.ofNullable(modelExpected));

        Endereco enderecoConsultado = consultarEnderecoPorIdUseCase.consultar(id);

        assertEquals(enderecoExpected.getId(),enderecoConsultado.getId());
        assertEquals(enderecoExpected.getCep(),enderecoConsultado.getCep());
        assertEquals(enderecoExpected.getNumero(),enderecoConsultado.getNumero());
        assertEquals(enderecoExpected.getUf(),enderecoConsultado.getUf());
        assertEquals(enderecoExpected.getPontoReferencia(),enderecoConsultado.getPontoReferencia());
        assertEquals(enderecoExpected.getRua(),enderecoConsultado.getRua());

        verify(enderecoPersist).findById(id);
        verify(mapper).toEndereco(modelExpected);
    }

    @Test
    void deveLancarUmErroQuandoNaoEncontrarEndereco() {
        when(enderecoPersist.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(EnderecoNaoEncontradoException.class,()->{
            consultarEnderecoPorIdUseCase.consultar(id);
        });

        verify(enderecoPersist).findById(id);
        verify(mapper,never()).toEndereco(modelExpected);
    }
}