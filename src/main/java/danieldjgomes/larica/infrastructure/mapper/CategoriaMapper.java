package danieldjgomes.larica.infrastructure.mapper;

import danieldjgomes.larica.core.categoria.dtos.CategoriaRequestDTO;
import danieldjgomes.larica.core.categoria.dtos.CategoriaResponseDTO;
import danieldjgomes.larica.core.categoria.entity.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "restauranteId", target = "restaurante.id")
    Categoria toEntity(CategoriaRequestDTO dto);

    @Mapping(source = "restaurante.id", target = "restauranteId")
    CategoriaResponseDTO toResponseDTO(Categoria entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "restauranteId", target = "restaurante.id")
    void updateEntityFromDTO(CategoriaRequestDTO dto, @MappingTarget Categoria entity);
}
