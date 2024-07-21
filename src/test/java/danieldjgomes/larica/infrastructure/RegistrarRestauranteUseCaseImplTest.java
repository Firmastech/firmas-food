package danieldjgomes.larica.infrastructure;

import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;
import danieldjgomes.larica.ports.database.RestaurantePersist;
import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.core.restaurante.entity.enums.StatusFuncionamento;
import danieldjgomes.larica.adapter.mapper.RestauranteMapper;
import danieldjgomes.larica.usecase.restaurante.RegistrarRestauranteUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegistrarRestauranteUseCaseImplTest {

    @Mock
    private RestaurantePersist restaurantePersist;

    @Mock
    private RestauranteMapper mapper;

    @InjectMocks
    private RegistrarRestauranteUseCaseImpl registrarRestauranteUseCase;

    private Restaurante restaurante;

    private RestauranteModel restauranteEntity;

    @BeforeEach
    void carregarRestaurantes(){
        UUID id = UUID.randomUUID();
        restaurante = new Restaurante();
        restaurante.setNome("Restaurante Novo");
        restaurante.setTempoEstimadoDeEntrega(30);
        restaurante.setStatusFuncionamento(StatusFuncionamento.INATIVO);

        restauranteEntity = new RestauranteModel();
        restauranteEntity.setId(id);
        restauranteEntity.setNome("Restaurante Novo");
        restauranteEntity.setTempoEstimadoDeEntrega(30);
        restauranteEntity.setStatusFuncionamento(StatusFuncionamento.INATIVO.name());
    }

    @Test
    void deveCadastrarUmRestauranteSemErros(){
        when(restaurantePersist.save(restauranteEntity)).thenReturn(restauranteEntity);
        when(mapper.toRestaurante(restauranteEntity)).thenReturn(restaurante);
        when(mapper.toEntity(restaurante)).thenReturn(restauranteEntity);

        Restaurante restauranteCadastrado = registrarRestauranteUseCase.registrarRestaurante(restaurante);

        assertNotNull(restauranteCadastrado.getId());
        assertEquals("Restaurante Novo",restauranteCadastrado.getNome());
        assertEquals(30, (int) restauranteCadastrado.getTempoEstimadoDeEntrega());
        assertEquals(StatusFuncionamento.INATIVO,restauranteCadastrado.getStatusFuncionamento());

        verify(restaurantePersist).save(restauranteEntity);
        verify(mapper).toRestaurante(restauranteEntity);
    }
}