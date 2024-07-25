package danieldjgomes.larica.usecase.endereco;

import danieldjgomes.larica.adapter.database.endereco.model.EnderecoModel;
import danieldjgomes.larica.adapter.mapper.EnderecoMapper;
import danieldjgomes.larica.core.endereco.entity.Endereco;
import danieldjgomes.larica.core.usecases.endereco.ConsultarEnderecoPorCepNumeroUseCase;
import danieldjgomes.larica.core.usecases.endereco.ConsultarEnderecoPorIdUseCase;
import danieldjgomes.larica.ports.database.EnderecoPersist;
import danieldjgomes.larica.usecase.endereco.exceptions.EnderecoInvalidoException;
import danieldjgomes.larica.usecase.endereco.exceptions.EnderecoNaoEncontradoException;
import danieldjgomes.larica.usecase.restaurante.exceptions.RestauranteNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarEnderecoUseCaseImplTest {

    @Mock
    private ConsultarEnderecoPorIdUseCase consultarEnderecoPorIdUseCase;

    @Mock
    private ConsultarEnderecoPorCepNumeroUseCase consultarEnderecoPorCepNumeroUseCase;

    @Mock
    private EnderecoPersist enderecoPersist;

    @Mock
    private EnderecoMapper mapper;

    @InjectMocks
    private AtualizarEnderecoUseCaseImpl atualizarEnderecoUseCase;

    private Endereco enderecoAtual;
    private Endereco enderecoExpected;
    private EnderecoModel modelExpected;
    private String id=UUID.randomUUID().toString();

    @BeforeEach
    void setUp() {
        enderecoAtual= new Endereco();
        enderecoAtual.setId(id);
        enderecoAtual.setRua("Rua Renan no Teste");
        enderecoAtual.setNumero("1234B");
        enderecoAtual.setCep("74185-321");
        enderecoAtual.setCidade("São Paulo");
        enderecoAtual.setUf("SP");
        enderecoAtual.setPontoReferencia("Proximo ao trintao");

        modelExpected= new EnderecoModel();
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
    void deveAtualizarEnderecoSemErros() {
        when(consultarEnderecoPorIdUseCase.consultar(id)).thenReturn(enderecoAtual);
        when(mapper.toModel(enderecoAtual)).thenReturn(modelExpected);
        when(enderecoPersist.update(modelExpected)).thenReturn(modelExpected);
        when(mapper.toEndereco(modelExpected)).thenReturn(enderecoExpected);

        Endereco enderecoUpdated = atualizarEnderecoUseCase.update(enderecoExpected);

        assertNotNull(enderecoUpdated);
        assertEquals(id, enderecoUpdated.getId());
        assertEquals("RJ", enderecoUpdated.getUf());
        assertEquals("Rua Atualizada", enderecoUpdated.getRua());
        assertEquals("85241-321", enderecoUpdated.getCep());
        assertEquals("Rio de Janeiro", enderecoUpdated.getCidade());
        assertEquals("4321A", enderecoUpdated.getNumero());
        assertEquals("Proximo ao cinquentão", enderecoUpdated.getPontoReferencia());

        verify(consultarEnderecoPorIdUseCase).consultar(id);
        verify(enderecoPersist).update(modelExpected);
        verify(mapper).toEndereco(modelExpected);
    }

    @Test
    void deveLancarUmaExceptionQuandoNaoEncontrarEndereco(){
        when(consultarEnderecoPorIdUseCase.consultar(id))
                .thenThrow(new EnderecoNaoEncontradoException("Endereco nao encontrado"));

        assertThrows(EnderecoNaoEncontradoException.class ,()->{
            atualizarEnderecoUseCase.update(enderecoExpected);
        });

        verify(enderecoPersist,never()).update(modelExpected);
        verify(enderecoPersist,never()).findById(id);
    }

    @Test
    void deveLancarUmaExceptionQuandoEncontrarEnderecoMesmoCEPeNumero(){
        final String cep = enderecoExpected.getCep();
        final String numero = enderecoExpected.getNumero();

        when(consultarEnderecoPorCepNumeroUseCase.consultar(cep,numero))
                .thenThrow(new EnderecoInvalidoException("Endereco Ja cadastrado"));

        assertThrows(EnderecoInvalidoException.class, () -> {
            atualizarEnderecoUseCase.update(enderecoExpected);
        });

        verify(enderecoPersist,never()).update(modelExpected);
        verify(enderecoPersist,never()).findById(id);
    }

}