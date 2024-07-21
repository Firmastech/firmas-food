package danieldjgomes.larica.usecase.restaurante;

import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;
import danieldjgomes.larica.ports.database.RestaurantePersist;
import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.usecase.restaurante.exceptions.RestauranteNotFoundException;
import danieldjgomes.larica.core.usecases.restaurante.AtualizarRestauranteUseCase;
import danieldjgomes.larica.adapter.mapper.RestauranteMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AtualizarRestauranteUseCaseImpl implements AtualizarRestauranteUseCase {

    private final RestaurantePersist restaurantePersist;
    private final RestauranteMapper mapper;

    @Override
    public Restaurante update(Restaurante restaurante) {
        RestauranteModel entityToUpdate = restaurantePersist.findById(restaurante.getId()).orElseThrow(
                ()-> new RestauranteNotFoundException("Restaurante nao encontrado")
        );
        Optional.ofNullable(restaurante.getNome()).ifPresent(entityToUpdate::setNome);
        Optional.ofNullable(restaurante.getStatusFuncionamento().name()).ifPresent(entityToUpdate::setStatusFuncionamento);
        Optional.ofNullable(restaurante.getTempoEstimadoDeEntrega()).ifPresent(entityToUpdate::setTempoEstimadoDeEntrega);

        return mapper.toRestaurante(restaurantePersist.update(entityToUpdate));
    }
}
