package danieldjgomes.larica.dataprovider.repository;

import danieldjgomes.larica.core.restaurante.entity.enums.StatusFuncionamento;
import danieldjgomes.larica.dataprovider.repository.entity.RestauranteEntity;
import danieldjgomes.larica.dataprovider.repository.mapper.RestauranteMapper;
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
class RestauranteRepositoryImplTest {

    @Mock
    private RestauranteDao restauranteDao;

    @InjectMocks
    private RestauranteRepositoryImpl repository;

    private RestauranteEntity restauranteEntity;

    private UUID id;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        restauranteEntity = new RestauranteEntity();
        restauranteEntity.setId(id);
        restauranteEntity.setNome("Restaurante Novo");
        restauranteEntity.setTempoEstimadoDeEntrega(30);
        restauranteEntity.setStatusFuncionamento(StatusFuncionamento.INATIVO.name());
    }


    @Test
    void deveRetornarUmRestauranteEntity() {
        when(restauranteDao.findById(id)).thenReturn(Optional.of(restauranteEntity));

        RestauranteEntity entity = repository.findById(id).get();

        assertNotNull(entity);
        assertEquals(id, entity.getId());
        assertEquals("Restaurante Novo", entity.getNome());
        assertEquals(StatusFuncionamento.INATIVO.name(), entity.getStatusFuncionamento());
        assertEquals(30, entity.getTempoEstimadoDeEntrega());
        assertEquals(id, restauranteEntity.getId());

        verify(restauranteDao).findById(id);
    }

    @Test
    void deveRetornarUmRestauranteEntityComMesmoNomePesquisado() {
        when(restauranteDao.findByNome("Restaurante Novo")).thenReturn(Optional.of(restauranteEntity));

        RestauranteEntity entity = repository.findByNome("Restaurante Novo").get();

        assertNotNull(entity);
        assertEquals(id, entity.getId());
        assertEquals("Restaurante Novo", entity.getNome());
        assertEquals(StatusFuncionamento.INATIVO.name(), entity.getStatusFuncionamento());
        assertEquals(30, entity.getTempoEstimadoDeEntrega());
        assertEquals(id, restauranteEntity.getId());

        verify(restauranteDao).findByNome("Restaurante Novo");
    }

    @Test
    void deveRetornarUmaEntityComOsDadosDeControleCriado() {
        RestauranteEntity entityToCreate = mock(RestauranteEntity.class);
        repository.save(entityToCreate);

        verify(entityToCreate).setDataInclusao(any(LocalDateTime.class));
        verify(entityToCreate).setIsActive(true);
        verify(restauranteDao).save(entityToCreate);
    }

    @Test
    void deveRetornarUmaEntityComOsDadosDeControleDeAtualizacao() {
        RestauranteEntity entityToUpdate = mock(RestauranteEntity.class);
        repository.update(entityToUpdate);
        verify(entityToUpdate).setDataAtualizacao(any(LocalDateTime.class));
        verify(restauranteDao).save(entityToUpdate);
    }

    @Test
    void deveSetarOsDadosDeAtivoAndDataExclusao() {
        RestauranteEntity entityToDelete = mock(RestauranteEntity.class);
        repository.delete(entityToDelete);
        verify(entityToDelete).setDataExclusao(any(LocalDateTime.class));
        verify(entityToDelete).setIsActive(false);
        verify(restauranteDao).save(entityToDelete);
    }
}