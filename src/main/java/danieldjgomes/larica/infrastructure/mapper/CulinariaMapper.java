package danieldjgomes.larica.infrastructure.mapper;

import danieldjgomes.larica.core.culinaria.dtos.CulinariaRequestDTO;
import danieldjgomes.larica.core.culinaria.dtos.CulinariaResponseDTO;
import danieldjgomes.larica.core.culinaria.entity.Culinaria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CulinariaMapper {

    @Mapping(source = "tipo", target = "tipo")
    Culinaria toEntity(CulinariaRequestDTO dto);

    @Mapping(source = "tipo", target = "tipo")
    CulinariaResponseDTO toDto(Culinaria entity);

    List<CulinariaResponseDTO> toDtoList(List<Culinaria> entities);
}
