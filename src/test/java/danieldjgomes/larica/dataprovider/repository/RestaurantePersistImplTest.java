package danieldjgomes.larica.dataprovider.repository;

import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;
import danieldjgomes.larica.app.adapter.database.restaurante.repository.RestauranteRepository;
import danieldjgomes.larica.app.usecase.restaurante.enums.StatusFuncionamento;
import danieldjgomes.larica.app.adapter.database.restaurante.impl.RestaurantePersistImpl;
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

@ExtendWith(MockitoExtension.class)
class RestaurantePersistImplTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @InjectMocks
    private RestaurantePersistImpl repository;

    private RestauranteEntity restauranteEntity;

    private String id;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID().toString();
        restauranteEntity = new RestauranteEntity();
        restauranteEntity.setId(id);
        restauranteEntity.setNome("Restaurante Novo");
        restauranteEntity.setTempoEstimadoDeEntrega(30);
        restauranteEntity.setStatusFuncionamento(StatusFuncionamento.INATIVO.name());
    }


    @Test
    void deveRetornarUmRestauranteEntity() {
        when(restauranteRepository.findByIdAndAtivoIsTrue(id)).thenReturn(Optional.of(restauranteEntity));

        RestauranteEntity entity = repository.findById(id).get();

        assertNotNull(entity);
        assertEquals(id, entity.getId());
        assertEquals("Restaurante Novo", entity.getNome());
        assertEquals(StatusFuncionamento.INATIVO.name(), entity.getStatusFuncionamento());
        assertEquals(30, entity.getTempoEstimadoDeEntrega());
        assertEquals(id, restauranteEntity.getId());

        verify(restauranteRepository).findByIdAndAtivoIsTrue(id);
    }

    @Test
    void deveRetornarUmRestauranteEntityComMesmoNomePesquisado() {
        when(restauranteRepository.findByNomeAndAtivoIsTrue("Restaurante Novo")).thenReturn(Optional.of(restauranteEntity));

        RestauranteEntity entity = repository.findByNome("Restaurante Novo").get();

        assertNotNull(entity);
        assertEquals(id, entity.getId());
        assertEquals("Restaurante Novo", entity.getNome());
        assertEquals(StatusFuncionamento.INATIVO.name(), entity.getStatusFuncionamento());
        assertEquals(30, entity.getTempoEstimadoDeEntrega());
        assertEquals(id, restauranteEntity.getId());

        verify(restauranteRepository).findByNomeAndAtivoIsTrue("Restaurante Novo");
    }

    @Test
    void deveRetornarUmaEntityComOsDadosDeControleCriado() {
        RestauranteEntity entityToCreate = mock(RestauranteEntity.class);
        repository.save(entityToCreate);

        verify(entityToCreate).setCriadoEm(any(LocalDateTime.class));
        verify(entityToCreate).setAtivo(true);
        verify(restauranteRepository).save(entityToCreate);
    }

    @Test
    void deveRetornarUmaEntityComOsDadosDeControleDeAtualizacao() {
        RestauranteEntity entityToUpdate = mock(RestauranteEntity.class);
        repository.update(entityToUpdate);
        verify(entityToUpdate).setAtualizadoEm(any(LocalDateTime.class));
        verify(restauranteRepository).save(entityToUpdate);
    }

    @Test
    void deveSetarOsDadosDeAtivoAndDataExclusao() {
        RestauranteEntity entityToDelete = mock(RestauranteEntity.class);
        repository.delete(entityToDelete);
        verify(entityToDelete).setDeletadoEm(any(LocalDateTime.class));
        verify(entityToDelete).setAtivo(false);
        verify(restauranteRepository).save(entityToDelete);
    }
}