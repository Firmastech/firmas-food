package danieldjgomes.larica.infrastructure;

import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;
import danieldjgomes.larica.core.restaurante.contract.RestauranteRepository;
import danieldjgomes.larica.core.restaurante.entity.enums.StatusFuncionamento;
import danieldjgomes.larica.core.restaurante.exceptions.EntityNotFoundException;

import danieldjgomes.larica.usecase.restaurante.InativarRestauranteUseCaseImpl;
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
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class InativarRestauranteUseCaseImplTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @InjectMocks
    private InativarRestauranteUseCaseImpl inativarRestauranteUseCase;

    private RestauranteModel restauranteEntity;

    private final String messageExceptionExpected = "Restaurante nao encontrado";

    @BeforeEach
    void carregarRestaurantes(){
        UUID id = UUID.randomUUID();
        restauranteEntity = new RestauranteModel();
        restauranteEntity.setId(id);
        restauranteEntity.setNome("Restaurante Inativado");
        restauranteEntity.setTempoEstimadoDeEntrega(45);
        restauranteEntity.setStatusFuncionamento(StatusFuncionamento.INATIVO.name());
    }

    @Test
    void deveInativarUmRestaurante(){
        UUID id = restauranteEntity.getId();
        when(restauranteRepository.findById(id)).thenReturn(Optional.of(restauranteEntity));

        inativarRestauranteUseCase.inativarRestaurante(id);

        verify(restauranteRepository).findById(id);
        verify(restauranteRepository).delete(restauranteEntity);
    }

    @Test
    void deveLancarUmaExceptionQuandoNaoEncontrarRestaurante(){
        UUID id = restauranteEntity.getId();
        when(restauranteRepository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,()->{
            inativarRestauranteUseCase.inativarRestaurante(id);
        });

        assertEquals(messageExceptionExpected,exception.getMessage());
        verify(restauranteRepository).findById(id);
        verify(restauranteRepository,never()).update(any());
    }
}