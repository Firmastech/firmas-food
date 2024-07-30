package danieldjgomes.larica.infrastructure.mapper;

import danieldjgomes.larica.app.adapter.database.categoria.model.CategoriaEntity;
import danieldjgomes.larica.app.usecase.categoria.request.CriarCategoriaRequest;
import danieldjgomes.larica.app.usecase.categoria.response.CategoriaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);


    @Mapping(target = "id", ignore = true)
    @Mapping(source = "restauranteId", target = "restaurante.id")
    CategoriaEntity toEntity(CriarCategoriaRequest dto);

    @Mapping(source = "restaurante.id", target = "restauranteId")
    CategoriaResponse toResponse(CategoriaEntity entity);

    @Mapping(source = "restaurante.id", target = "restauranteId")
    CategoriaResponse updateEntityFromDTO(CategoriaEntity categoria);
}
