package danieldjgomes.larica.dataprovider.repository;

import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.dataprovider.repository.entity.RestauranteEntity;
import danieldjgomes.larica.infrastructure.dto.restaurante.response.ConsultarRestauranteResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestauranteMapper {

    RestauranteEntity from(Restaurante restaurante);
    ConsultarRestauranteResponseDTO to(RestauranteEntity restaurante);
}
