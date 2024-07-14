package danieldjgomes.larica.infrastructure;

import danieldjgomes.larica.core.restaurante.contract.RestauranteRepository;
import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.core.restaurante.exceptions.EntityNotFoundException;
import danieldjgomes.larica.core.usecases.restaurante.AtualizarRestauranteUseCase;
import danieldjgomes.larica.dataprovider.repository.RestauranteMapper;
import danieldjgomes.larica.dataprovider.repository.entity.RestauranteEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class AtualizarRestauranteUseCaseImpl implements AtualizarRestauranteUseCase {

    private final RestauranteRepository restauranteRepository;
    private final RestauranteMapper mapper;

    @Override
    public Restaurante update(Restaurante restaurante) {
        RestauranteEntity entityToUpdate = restauranteRepository.findById(restaurante.getId()).orElseThrow(
                ()-> new EntityNotFoundException("Restaurante nao encontrado")
        );
        Optional.ofNullable(restaurante.getNome()).ifPresent(entityToUpdate::setNome);
        Optional.ofNullable(restaurante.getStatusFuncionamento().name()).ifPresent(entityToUpdate::setStatusFuncionamento);
        Optional.ofNullable(restaurante.getTempoEstimadoDeEntrega()).ifPresent(entityToUpdate::setTempoEstimadoDeEntrega);

        return mapper.toRestaurante(restauranteRepository.update(entityToUpdate));
    }
}
