package danieldjgomes.larica.adapter.database.endereco.impl;

import danieldjgomes.larica.app.adapter.database.endereco.impl.EnderecoPersistImpl;
import danieldjgomes.larica.app.adapter.database.endereco.model.EnderecoEntity;
import danieldjgomes.larica.app.adapter.database.endereco.repository.EnderecoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EnderecoPersistImplTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoPersistImpl enderecoPersist;

    private EnderecoEntity modelExpected;
    private String id = UUID.randomUUID().toString();

    @BeforeEach
    void setup(){
        modelExpected= new EnderecoEntity();
        modelExpected.setId(id);
        modelExpected.setRua("Rua Atualizada");
        modelExpected.setNumero("4321A");
        modelExpected.setCep("85241-321");
        modelExpected.setCidade("Rio de Janeiro");
        modelExpected.setUf("RJ");
        modelExpected.setPontoReferencia("Proximo ao cinquentão");
    }

    @Test
    void deveRetornarUmRestauranteEntityComMesmoNomePesquisado() {
        final String cep = modelExpected.getCep();
        final String numero = modelExpected.getNumero();
        when(enderecoPersist.findByCEPandNumero(cep,numero)).thenReturn(Optional.of(modelExpected));

        EnderecoEntity modelConsultada = enderecoPersist.findByCEPandNumero(cep,numero).get();

        assertEquals(modelExpected.getId(),modelConsultada.getId());
        assertEquals(modelExpected.getCep(),modelConsultada.getCep());
        assertEquals(modelExpected.getNumero(),modelConsultada.getNumero());
        assertEquals(modelExpected.getUf(),modelConsultada.getUf());
        assertEquals(modelExpected.getPontoReferencia(),modelConsultada.getPontoReferencia());
        assertEquals(modelExpected.getRua(),modelConsultada.getRua());
        verify(enderecoRepository).findByCepAndNumeroAndAtivo(cep,numero,true);
    }

    @Test
    void deveRetornarUmaEntityComOsDadosDeControleCriado() {
        EnderecoEntity entityToCreate = mock(EnderecoEntity.class);
        enderecoPersist.save(entityToCreate);

        verify(entityToCreate).setCriadoEm(any(LocalDateTime.class));
        verify(entityToCreate).setAtivo(true);
        verify(enderecoRepository).save(entityToCreate);
    }

    @Test
    void deveRetornarUmaEntityComOsDadosDeControleDeAtualizacao() {
        EnderecoEntity entityToUpdate = mock(EnderecoEntity.class);
        enderecoPersist.update(entityToUpdate);
        verify(entityToUpdate).setAtualizadoEm(any(LocalDateTime.class));
        verify(enderecoRepository).save(entityToUpdate);
    }

    @Test
    void deveSetarOsDadosDeAtivoAndDataExclusao() {
        EnderecoEntity entityToDelete = mock(EnderecoEntity.class);
        enderecoPersist.inativar(entityToDelete);
        verify(entityToDelete).setDeletadoEm(any(LocalDateTime.class));
        verify(entityToDelete).setAtivo(false);
        verify(enderecoRepository).save(entityToDelete);
    }

}