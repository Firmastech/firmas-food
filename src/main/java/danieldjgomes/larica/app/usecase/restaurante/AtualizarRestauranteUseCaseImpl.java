package danieldjgomes.larica.app.usecase.restaurante;

import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;
import danieldjgomes.larica.app.ports.database.RestaurantePersist;
import danieldjgomes.larica.app.usecase.restaurante.response.Restaurante;
import danieldjgomes.larica.app.usecase.restaurante.exceptions.RestauranteNotFoundException;
import danieldjgomes.larica.app.usecase.restaurante.port.AtualizarRestauranteUseCase;
import danieldjgomes.larica.app.adapter.mapper.RestauranteMapper;
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
        RestauranteEntity entityToUpdate = restaurantePersist.findById(restaurante.getId()).orElseThrow(
                RestauranteNotFoundException::new
        );
        Optional.ofNullable(restaurante.getNome()).ifPresent(entityToUpdate::setNome);
        Optional.of(restaurante.getStatusFuncionamento().name()).ifPresent(entityToUpdate::setStatusFuncionamento);
        Optional.ofNullable(restaurante.getTempoEstimadoDeEntrega()).ifPresent(entityToUpdate::setTempoEstimadoDeEntrega);

        return mapper.toRestaurante(restaurantePersist.update(entityToUpdate));
    }
}
