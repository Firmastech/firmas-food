package danieldjgomes.larica.usecase.restaurante;

import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;
import danieldjgomes.larica.app.usecase.restaurante.ConsultarRestauranteUseCaseImpl;
import danieldjgomes.larica.app.ports.database.RestaurantePersist;
import danieldjgomes.larica.app.usecase.restaurante.response.Restaurante;
import danieldjgomes.larica.app.usecase.restaurante.enums.StatusFuncionamento;
import danieldjgomes.larica.app.usecase.restaurante.exceptions.RestauranteNotFoundException;
import danieldjgomes.larica.app.adapter.mapper.RestauranteMapper;
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
class ConsultarRestauranteUseCaseImplTest {

    @Mock
    private RestaurantePersist restaurantePersist;

    @Mock
    private RestauranteMapper mapper;

    @InjectMocks
    private ConsultarRestauranteUseCaseImpl consultarRestauranteUseCase;

    private Restaurante restaurante;

    private RestauranteEntity restauranteEntity;

    private final String messageExceptionExpected = "Restaurante nao encontrado";

    @BeforeEach
    void carregarRestaurantes(){
        String id = UUID.randomUUID().toString();
        restaurante = new Restaurante();
        restaurante.setId(id);
        restaurante.setNome("Restaurante Consultado");
        restaurante.setTempoEstimadoDeEntrega(30);
        restaurante.setStatusFuncionamento(StatusFuncionamento.ABERTO);

        restauranteEntity = new RestauranteEntity();
        restauranteEntity.setId(id);
        restauranteEntity.setNome("Restaurante Consultado");
        restauranteEntity.setTempoEstimadoDeEntrega(30);
        restauranteEntity.setStatusFuncionamento(StatusFuncionamento.ABERTO.name());
    }

    @Test
    void deveConsultarUmRestauranteSemErros(){
        String id = restaurante.getId();
        when(restaurantePersist.findById(id)).thenReturn(Optional.of(restauranteEntity));
        when(mapper.toRestaurante(restauranteEntity)).thenReturn(restaurante);

        Restaurante restauranteConsultado = consultarRestauranteUseCase.consultar(id);

        assertEquals(restaurante.getId(),restauranteConsultado.getId());
        assertEquals(restaurante.getNome(),restauranteConsultado.getNome());
        assertEquals(restaurante.getStatusFuncionamento(),restauranteConsultado.getStatusFuncionamento());
        assertEquals(restaurante.getTempoEstimadoDeEntrega(),restauranteConsultado.getTempoEstimadoDeEntrega());

        verify(restaurantePersist).findById(id);
        verify(mapper).toRestaurante(restauranteEntity);
    }

    @Test
    void deveLancarUmaExceptionQuandoNaoEncontrarRestaurante(){
        String id = restaurante.getId();
        when(restaurantePersist.findById(restaurante.getId())).thenReturn(Optional.empty());

        RestauranteNotFoundException exception = assertThrows(RestauranteNotFoundException.class,()->{
            consultarRestauranteUseCase.consultar(id);
        });

        assertEquals(messageExceptionExpected,exception.getMessage());
        verify(restaurantePersist).findById(restaurante.getId());
        verify(mapper,never()).toRestaurante(any());
    }
}