package danieldjgomes.larica.dataprovider.repository;

import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.dataprovider.repository.entity.RestauranteEntity;
import danieldjgomes.larica.infrastructure.dto.restaurante.response.ConsultarRestauranteResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

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
    ConsultarRestauranteResponseDTO toDTO(RestauranteEntity restaurante);

    @Mapping(source = "id",target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "tempoEstimadoDeEntrega", target = "tempoEstimadoDeEntrega")
    @Mapping(source = "statusFuncionamento", target = "statusFuncionamento")
    Restaurante toRestaurante(RestauranteEntity entity);

}
