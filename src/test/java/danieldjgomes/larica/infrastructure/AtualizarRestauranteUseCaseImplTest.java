package danieldjgomes.larica.infrastructure;

import danieldjgomes.larica.ports.database.RestaurantePersist;
import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.core.restaurante.entity.enums.StatusFuncionamento;
import danieldjgomes.larica.usecase.restaurante.exceptions.RestauranteNotFoundException;
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
    private RestaurantePersist restaurantePersist;

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
        when(restaurantePersist.findById(restaurante.getId())).thenReturn(Optional.of(restauranteEntity));
        when(restaurantePersist.update(restauranteEntity)).thenReturn(restauranteEntity);
        when(mapper.toRestaurante(restauranteEntity)).thenReturn(restaurante);

        Restaurante updatedRestaurante = atualizarRestauranteUseCase.update(restaurante);

        assertEquals("Restaurante Atualizado",updatedRestaurante.getNome());
        assertEquals(30, (int) updatedRestaurante.getTempoEstimadoDeEntrega());
        assertEquals(StatusFuncionamento.ABERTO,updatedRestaurante.getStatusFuncionamento());

        verify(restaurantePersist).findById(restaurante.getId());
        verify(restaurantePersist).update(restauranteEntity);
        verify(mapper).toRestaurante(restauranteEntity);
    }

    @Test
    void deveLancarUmaExceptionQuandoNaoEncontrarRestaurante(){
        when(restaurantePersist.findById(restaurante.getId())).thenReturn(Optional.empty());

        RestauranteNotFoundException exception = assertThrows(RestauranteNotFoundException.class,()->{
            atualizarRestauranteUseCase.update(restaurante);
        });

        assertEquals(messageExceptionExpected,exception.getMessage());
        verify(restaurantePersist).findById(restaurante.getId());
        verify(restaurantePersist,never()).update(any());
        verify(mapper,never()).toRestaurante(any());
    }
}