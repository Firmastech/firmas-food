package danieldjgomes.larica.dataprovider.repository;

import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;
import danieldjgomes.larica.adapter.database.restaurante.repository.RestauranteRepository;
import danieldjgomes.larica.core.restaurante.entity.enums.StatusFuncionamento;
import danieldjgomes.larica.adapter.database.restaurante.impl.RestaurantePersistImpl;
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

@ExtendWith(MockitoExtension.class)
class RestaurantePersistImplTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @InjectMocks
    private RestaurantePersistImpl repository;

    private RestauranteModel restauranteEntity;

    private UUID id;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        restauranteEntity = new RestauranteModel();
        restauranteEntity.setId(id);
        restauranteEntity.setNome("Restaurante Novo");
        restauranteEntity.setTempoEstimadoDeEntrega(30);
        restauranteEntity.setStatusFuncionamento(StatusFuncionamento.INATIVO.name());
    }


    @Test
    void deveRetornarUmRestauranteEntity() {
        when(restauranteRepository.findById(id)).thenReturn(Optional.of(restauranteEntity));

        RestauranteModel entity = repository.findById(id).get();

        assertNotNull(entity);
        assertEquals(id, entity.getId());
        assertEquals("Restaurante Novo", entity.getNome());
        assertEquals(StatusFuncionamento.INATIVO.name(), entity.getStatusFuncionamento());
        assertEquals(30, entity.getTempoEstimadoDeEntrega());
        assertEquals(id, restauranteEntity.getId());

        verify(restauranteRepository).findById(id);
    }

    @Test
    void deveRetornarUmRestauranteEntityComMesmoNomePesquisado() {
        when(restauranteRepository.findByNome("Restaurante Novo")).thenReturn(Optional.of(restauranteEntity));

        RestauranteModel entity = repository.findByNome("Restaurante Novo").get();

        assertNotNull(entity);
        assertEquals(id, entity.getId());
        assertEquals("Restaurante Novo", entity.getNome());
        assertEquals(StatusFuncionamento.INATIVO.name(), entity.getStatusFuncionamento());
        assertEquals(30, entity.getTempoEstimadoDeEntrega());
        assertEquals(id, restauranteEntity.getId());

        verify(restauranteRepository).findByNome("Restaurante Novo");
    }

    @Test
    void deveRetornarUmaEntityComOsDadosDeControleCriado() {
        RestauranteModel entityToCreate = mock(RestauranteModel.class);
        repository.save(entityToCreate);

        verify(entityToCreate).setDataInclusao(any(LocalDateTime.class));
        verify(entityToCreate).setIsActive(true);
        verify(restauranteRepository).save(entityToCreate);
    }

    @Test
    void deveRetornarUmaEntityComOsDadosDeControleDeAtualizacao() {
        RestauranteModel entityToUpdate = mock(RestauranteModel.class);
        repository.update(entityToUpdate);
        verify(entityToUpdate).setDataAtualizacao(any(LocalDateTime.class));
        verify(restauranteRepository).save(entityToUpdate);
    }

    @Test
    void deveSetarOsDadosDeAtivoAndDataExclusao() {
        RestauranteModel entityToDelete = mock(RestauranteModel.class);
        repository.delete(entityToDelete);
        verify(entityToDelete).setDataExclusao(any(LocalDateTime.class));
        verify(entityToDelete).setIsActive(false);
        verify(restauranteRepository).save(entityToDelete);
    }
}