package danieldjgomes.larica.usecase.endereco;


import danieldjgomes.larica.app.adapter.database.endereco.model.EnderecoEntity;
import danieldjgomes.larica.app.adapter.mapper.EnderecoMapper;
import danieldjgomes.larica.app.usecase.endereco.InativarEnderecoUseCaseImpl;
import danieldjgomes.larica.app.usecase.endereco.response.Endereco;
import danieldjgomes.larica.app.usecase.endereco.port.ConsultarEnderecoPorIdUseCase;
import danieldjgomes.larica.app.ports.database.EnderecoPersist;
import danieldjgomes.larica.app.usecase.endereco.exceptions.EnderecoNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InativarEnderecoUseCaseImplTest {

    @Mock
    private EnderecoPersist enderecoPersist;

    @Mock
    private ConsultarEnderecoPorIdUseCase consultarEnderecoPorIdUseCase;

    @Mock
    private EnderecoMapper mapper;

    @InjectMocks
    private InativarEnderecoUseCaseImpl inativarEnderecoUseCase;

    private EnderecoEntity modelExpected;
    private Endereco enderecoExpected;

    private final String messageExceptionExpected = "Endereco nao encontrado";

    @BeforeEach
    void setup(){
        String id = UUID.randomUUID().toString();
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
    void deveInativarUmEndereco(){
        String id = modelExpected.getId();
        when(mapper.toModel(enderecoExpected)).thenReturn(modelExpected);
        when(consultarEnderecoPorIdUseCase.consultar(id))
                .thenReturn(enderecoExpected);

        inativarEnderecoUseCase.inativar(id);

        verify(consultarEnderecoPorIdUseCase).consultar(id);
        verify(enderecoPersist).inativar(modelExpected);
    }

    @Test
    void deveLancarUmaExceptionQuandoNaoEncontrarEndereco(){
        String id = modelExpected.getId();
        when(consultarEnderecoPorIdUseCase.consultar(id))
                .thenThrow(new EnderecoNaoEncontradoException("Endereco nao encontrado"));

        EnderecoNaoEncontradoException exception = assertThrows(EnderecoNaoEncontradoException.class,()->{
            inativarEnderecoUseCase.inativar(id);
        });

        assertEquals(messageExceptionExpected,exception.getMessage());
        verify(enderecoPersist,never()).update(any());
    }
}