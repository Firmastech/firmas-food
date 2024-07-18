package danieldjgomes.larica.infrastructure;

import danieldjgomes.larica.core.restaurante.contract.RestauranteRepository;
import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.core.restaurante.exceptions.EntityNotFoundException;
import danieldjgomes.larica.core.usecases.restaurante.ConsultarRestauranteUseCase;
import danieldjgomes.larica.dataprovider.repository.mapper.RestauranteMapper;
import danieldjgomes.larica.dataprovider.repository.entity.RestauranteEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class ConsultarRestauranteUseCaseImpl implements ConsultarRestauranteUseCase {

    private final RestauranteRepository restauranteRepository;
    private final RestauranteMapper mapper;

    @Override
     public Restaurante consultar(UUID ID) {
        RestauranteEntity entity = restauranteRepository.findById(ID).orElseThrow(
                ()-> new EntityNotFoundException("Restaurante nao encontrado")
        );
        return mapper.toRestaurante(entity);
     }
}
