package danieldjgomes.larica.usecase.restaurante;

import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;
import danieldjgomes.larica.ports.database.RestaurantePersist;
import danieldjgomes.larica.core.restaurante.entity.enums.StatusFuncionamento;
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
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class InativarRestauranteUseCaseImplTest {

    @Mock
    private RestaurantePersist restaurantePersist;

    @InjectMocks
    private InativarRestauranteUseCaseImpl inativarRestauranteUseCase;

    private RestauranteModel restauranteEntity;

    private final String messageExceptionExpected = "Restaurante nao encontrado";

    @BeforeEach
    void carregarRestaurantes(){
        String id = UUID.randomUUID().toString();
        restauranteEntity = new RestauranteModel();
        restauranteEntity.setId(id);
        restauranteEntity.setNome("Restaurante Inativado");
        restauranteEntity.setTempoEstimadoDeEntrega(45);
        restauranteEntity.setStatusFuncionamento(StatusFuncionamento.INATIVO.name());
    }

    @Test
    void deveInativarUmRestaurante(){
        String id = restauranteEntity.getId().toString();
        when(restaurantePersist.findById(id)).thenReturn(Optional.of(restauranteEntity));

        inativarRestauranteUseCase.inativarRestaurante(id);

        verify(restaurantePersist).findById(id);
        verify(restaurantePersist).delete(restauranteEntity);
    }

    @Test
    void deveLancarUmaExceptionQuandoNaoEncontrarRestaurante(){
        String id = restauranteEntity.getId();
        when(restaurantePersist.findById(id)).thenReturn(Optional.empty());

        RestauranteNotFoundException exception = assertThrows(RestauranteNotFoundException.class,()->{
            inativarRestauranteUseCase.inativarRestaurante(id);
        });

        assertEquals(messageExceptionExpected,exception.getMessage());
        verify(restaurantePersist).findById(id);
        verify(restaurantePersist,never()).update(any());
    }
}