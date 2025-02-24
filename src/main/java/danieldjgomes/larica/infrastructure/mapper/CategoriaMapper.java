package danieldjgomes.larica.infrastructure.mapper;

import danieldjgomes.larica.app.usecase.categoria.response.CategoriaResponse;
import danieldjgomes.larica.core.categoria.dtos.AtualizarCategoriaResponse;
import danieldjgomes.larica.core.categoria.dtos.CriarCategoriaRequest;
import danieldjgomes.larica.core.categoria.dtos.CriarCategoriaResponse;
import danieldjgomes.larica.core.categoria.entity.CategoriaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    @Mapping(target = "id", ignore = true)
    CategoriaEntity toEntity(CriarCategoriaRequest dto);

    CategoriaResponse toResponseDTO(CategoriaEntity entity);
    CriarCategoriaResponse toCriarCategoriaResponse(CategoriaEntity entity);

    AtualizarCategoriaResponse updateEntityFromDTO(CategoriaEntity entity);
}
