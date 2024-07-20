package danieldjgomes.larica.infrastructure;

import danieldjgomes.larica.core.restaurante.contract.RestauranteRepository;
import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.core.restaurante.entity.enums.StatusFuncionamento;
import danieldjgomes.larica.core.restaurante.exceptions.EntityNotFoundException;
import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;
import danieldjgomes.larica.adapter.mapper.RestauranteMapper;
import danieldjgomes.larica.usecase.restaurante.AtualizarRestauranteUseCaseImpl;
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
class AtualizarRestauranteUseCaseImplTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private RestauranteMapper mapper;

    @InjectMocks
    private AtualizarRestauranteUseCaseImpl atualizarRestauranteUseCase;

    private Restaurante restaurante;

    private RestauranteModel restauranteEntity;

    private final String messageExceptionExpected = "Restaurante nao encontrado";

    @BeforeEach
    void carregarRestaurantes(){
        UUID id = UUID.randomUUID();
        restaurante = new Restaurante();
        restaurante.setId(id);
        restaurante.setNome("Restaurante Atualizado");
        restaurante.setTempoEstimadoDeEntrega(30);
        restaurante.setStatusFuncionamento(StatusFuncionamento.ABERTO);

        restauranteEntity = new RestauranteModel();
        restauranteEntity.setId(id);
        restauranteEntity.setNome("Restaurante Desatualizado");
        restauranteEntity.setTempoEstimadoDeEntrega(45);
        restauranteEntity.setStatusFuncionamento(StatusFuncionamento.FECHADO.name());
    }

    @Test
    void deveAtualizarUmRestauranteSemErros(){
        when(restauranteRepository.findById(restaurante.getId())).thenReturn(Optional.of(restauranteEntity));
        when(restauranteRepository.update(restauranteEntity)).thenReturn(restauranteEntity);
        when(mapper.toRestaurante(restauranteEntity)).thenReturn(restaurante);

        Restaurante updatedRestaurante = atualizarRestauranteUseCase.update(restaurante);

        assertEquals("Restaurante Atualizado",updatedRestaurante.getNome());
        assertEquals(30, (int) updatedRestaurante.getTempoEstimadoDeEntrega());
        assertEquals(StatusFuncionamento.ABERTO,updatedRestaurante.getStatusFuncionamento());

        verify(restauranteRepository).findById(restaurante.getId());
        verify(restauranteRepository).update(restauranteEntity);
        verify(mapper).toRestaurante(restauranteEntity);
    }

    @Test
    void deveLancarUmaExceptionQuandoNaoEncontrarRestaurante(){
        when(restauranteRepository.findById(restaurante.getId())).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,()->{
            atualizarRestauranteUseCase.update(restaurante);
        });

        assertEquals(messageExceptionExpected,exception.getMessage());
        verify(restauranteRepository).findById(restaurante.getId());
        verify(restauranteRepository,never()).update(any());
        verify(mapper,never()).toRestaurante(any());
    }
}