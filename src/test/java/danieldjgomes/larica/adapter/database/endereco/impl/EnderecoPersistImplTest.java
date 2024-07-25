package danieldjgomes.larica.adapter.database.endereco.impl;

import danieldjgomes.larica.adapter.database.endereco.model.EnderecoModel;
import danieldjgomes.larica.adapter.database.endereco.repository.EnderecoRepository;
import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;
import danieldjgomes.larica.core.restaurante.entity.enums.StatusFuncionamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
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

    private EnderecoModel modelExpected;
    private String id = UUID.randomUUID().toString();

    @BeforeEach
    void setup(){
        modelExpected= new EnderecoModel();
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

        EnderecoModel modelConsultada = enderecoPersist.findByCEPandNumero(cep,numero).get();

        assertEquals(modelExpected.getId(),modelConsultada.getId());
        assertEquals(modelExpected.getCep(),modelConsultada.getCep());
        assertEquals(modelExpected.getNumero(),modelConsultada.getNumero());
        assertEquals(modelExpected.getUf(),modelConsultada.getUf());
        assertEquals(modelExpected.getPontoReferencia(),modelConsultada.getPontoReferencia());
        assertEquals(modelExpected.getRua(),modelConsultada.getRua());
        verify(enderecoRepository).findByCepAndNumero(cep,numero);
    }

    @Test
    void deveRetornarUmaEntityComOsDadosDeControleCriado() {
        EnderecoModel entityToCreate = mock(EnderecoModel.class);
        enderecoPersist.save(entityToCreate);

        verify(entityToCreate).setCriadoEm(any(LocalDateTime.class));
        verify(entityToCreate).setAtivo(true);
        verify(enderecoRepository).save(entityToCreate);
    }

    @Test
    void deveRetornarUmaEntityComOsDadosDeControleDeAtualizacao() {
        EnderecoModel entityToUpdate = mock(EnderecoModel.class);
        enderecoPersist.update(entityToUpdate);
        verify(entityToUpdate).setAtualizadoEm(any(LocalDateTime.class));
        verify(enderecoRepository).save(entityToUpdate);
    }

    @Test
    void deveSetarOsDadosDeAtivoAndDataExclusao() {
        EnderecoModel entityToDelete = mock(EnderecoModel.class);
        enderecoPersist.inativar(entityToDelete);
        verify(entityToDelete).setDeletadoEm(any(LocalDateTime.class));
        verify(entityToDelete).setAtivo(false);
        verify(enderecoRepository).save(entityToDelete);
    }

}