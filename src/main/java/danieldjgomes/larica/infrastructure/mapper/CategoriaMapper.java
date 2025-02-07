package danieldjgomes.larica.infrastructure.mapper;

import danieldjgomes.larica.core.categoria.dtos.CategoriaRequestDTO;
import danieldjgomes.larica.core.categoria.dtos.CategoriaResponseDTO;
import danieldjgomes.larica.core.categoria.entity.CategoriaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    @Mapping(target = "id", ignore = true)
    CategoriaEntity toEntity(CategoriaRequestDTO dto);

    CategoriaResponseDTO toResponseDTO(CategoriaEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "nome", target = "nome")
    void updateEntityFromDTO(CategoriaRequestDTO dto, @MappingTarget CategoriaEntity entity);
}
