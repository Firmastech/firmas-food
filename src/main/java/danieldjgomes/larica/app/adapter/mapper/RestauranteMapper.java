package danieldjgomes.larica.app.adapter.mapper;

import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;
import danieldjgomes.larica.app.usecase.restaurante.response.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestauranteMapper {

    @Mapping(source = "id",target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "tempoEstimadoDeEntrega", target = "tempoEstimadoDeEntrega")
    @Mapping(source = "statusFuncionamento", target = "statusFuncionamento")
    RestauranteEntity toEntity(Restaurante restaurante);

    @Mapping(source = "id",target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "tempoEstimadoDeEntrega", target = "tempoEstimadoDeEntrega")
    @Mapping(source = "statusFuncionamento", target = "statusFuncionamento")
    Restaurante toRestaurante(RestauranteEntity entity);

}
