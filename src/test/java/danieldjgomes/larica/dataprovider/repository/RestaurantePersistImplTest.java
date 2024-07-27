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

    private RestauranteModel restauranteEntity;

    private String id;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID().toString();
        restauranteEntity = new RestauranteModel();
        restauranteEntity.setId(id);
        restauranteEntity.setNome("Restaurante Novo");
        restauranteEntity.setTempoEstimadoDeEntrega(30);
        restauranteEntity.setStatusFuncionamento(StatusFuncionamento.INATIVO.name());
    }


    @Test
    void deveRetornarUmRestauranteEntity() {
        when(restauranteRepository.findByIdAndAtivo(id,true)).thenReturn(Optional.of(restauranteEntity));

        RestauranteModel entity = repository.findById(id).get();

        assertNotNull(entity);
        assertEquals(id, entity.getId());
        assertEquals("Restaurante Novo", entity.getNome());
        assertEquals(StatusFuncionamento.INATIVO.name(), entity.getStatusFuncionamento());
        assertEquals(30, entity.getTempoEstimadoDeEntrega());
        assertEquals(id, restauranteEntity.getId());

        verify(restauranteRepository).findByIdAndAtivo(id,true);
    }

    @Test
    void deveRetornarUmRestauranteEntityComMesmoNomePesquisado() {
        when(restauranteRepository.findByNomeAndAtivo("Restaurante Novo",true)).thenReturn(Optional.of(restauranteEntity));

        RestauranteModel entity = repository.findByNome("Restaurante Novo").get();

        assertNotNull(entity);
        assertEquals(id, entity.getId());
        assertEquals("Restaurante Novo", entity.getNome());
        assertEquals(StatusFuncionamento.INATIVO.name(), entity.getStatusFuncionamento());
        assertEquals(30, entity.getTempoEstimadoDeEntrega());
        assertEquals(id, restauranteEntity.getId());

        verify(restauranteRepository).findByNomeAndAtivo("Restaurante Novo",true);
    }

    @Test
    void deveRetornarUmaEntityComOsDadosDeControleCriado() {
        RestauranteModel entityToCreate = mock(RestauranteModel.class);
        repository.save(entityToCreate);

        verify(entityToCreate).setCriadoEm(any(Date.class));
        verify(entityToCreate).setAtivo(true);
        verify(restauranteRepository).save(entityToCreate);
    }

    @Test
    void deveRetornarUmaEntityComOsDadosDeControleDeAtualizacao() {
        RestauranteModel entityToUpdate = mock(RestauranteModel.class);
        repository.update(entityToUpdate);
        verify(entityToUpdate).setAtualizadoEm(any(Date.class));
        verify(restauranteRepository).save(entityToUpdate);
    }

    @Test
    void deveSetarOsDadosDeAtivoAndDataExclusao() {
        RestauranteModel entityToDelete = mock(RestauranteModel.class);
        repository.delete(entityToDelete);
        verify(entityToDelete).setDeletadoEm(any(Date.class));
        verify(entityToDelete).setAtivo(false);
        verify(restauranteRepository).save(entityToDelete);
    }
}