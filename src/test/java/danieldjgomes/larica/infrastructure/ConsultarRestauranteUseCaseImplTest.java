package danieldjgomes.larica.infrastructure;

import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;
import danieldjgomes.larica.core.restaurante.contract.RestauranteRepository;
import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.core.restaurante.entity.enums.StatusFuncionamento;
import danieldjgomes.larica.core.restaurante.exceptions.EntityNotFoundException;
import danieldjgomes.larica.adapter.mapper.RestauranteMapper;
import danieldjgomes.larica.usecase.restaurante.ConsultarRestauranteUseCaseImpl;
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
    private RestauranteRepository restauranteRepository;

    @Mock
    private RestauranteMapper mapper;

    @InjectMocks
    private ConsultarRestauranteUseCaseImpl consultarRestauranteUseCase;

    private Restaurante restaurante;

    private RestauranteModel restauranteEntity;

    private final String messageExceptionExpected = "Restaurante nao encontrado";

    @BeforeEach
    void carregarRestaurantes(){
        UUID id = UUID.randomUUID();
        restaurante = new Restaurante();
        restaurante.setId(id);
        restaurante.setNome("Restaurante Consultado");
        restaurante.setTempoEstimadoDeEntrega(30);
        restaurante.setStatusFuncionamento(StatusFuncionamento.ABERTO);

        restauranteEntity = new RestauranteModel();
        restauranteEntity.setId(id);
        restauranteEntity.setNome("Restaurante Consultado");
        restauranteEntity.setTempoEstimadoDeEntrega(30);
        restauranteEntity.setStatusFuncionamento(StatusFuncionamento.ABERTO.name());
    }

    @Test
    void deveConsultarUmRestauranteSemErros(){
        UUID id = restaurante.getId();
        when(restauranteRepository.findById(id)).thenReturn(Optional.of(restauranteEntity));
        when(mapper.toRestaurante(restauranteEntity)).thenReturn(restaurante);

        Restaurante restauranteConsultado = consultarRestauranteUseCase.consultar(id);

        assertEquals(restaurante.getId(),restauranteConsultado.getId());
        assertEquals(restaurante.getNome(),restauranteConsultado.getNome());
        assertEquals(restaurante.getStatusFuncionamento(),restauranteConsultado.getStatusFuncionamento());
        assertEquals(restaurante.getTempoEstimadoDeEntrega(),restauranteConsultado.getTempoEstimadoDeEntrega());

        verify(restauranteRepository).findById(id);
        verify(mapper).toRestaurante(restauranteEntity);
    }

    @Test
    void deveLancarUmaExceptionQuandoNaoEncontrarRestaurante(){
        UUID id = restaurante.getId();
        when(restauranteRepository.findById(restaurante.getId())).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,()->{
            consultarRestauranteUseCase.consultar(id);
        });

        assertEquals(messageExceptionExpected,exception.getMessage());
        verify(restauranteRepository).findById(restaurante.getId());
        verify(mapper,never()).toRestaurante(any());
    }
}