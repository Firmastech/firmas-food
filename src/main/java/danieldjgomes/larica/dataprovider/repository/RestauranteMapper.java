package danieldjgomes.larica.dataprovider.repository;

import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.dataprovider.repository.entity.RestauranteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestauranteMapper {

    RestauranteEntity from(Restaurante restaurante);
}
