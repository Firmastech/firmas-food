package danieldjgomes.larica.infrastructure.mapper;

import danieldjgomes.larica.core.prato.dtos.PratoRequestDTO;
import danieldjgomes.larica.core.prato.dtos.PratoResponseDTO;
import danieldjgomes.larica.core.prato.entity.Prato;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface PratoMapper {

    PratoMapper INSTANCE = Mappers.getMapper(PratoMapper.class);

    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "descricao", target = "descricao")
    @Mapping(source = "urlImagem", target = "urlImagem")
    @Mapping(source = "preco", target = "preco")
    @Mapping(source = "desconto", target = "desconto")
    Prato toEntity(PratoRequestDTO dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "descricao", target = "descricao")
    @Mapping(source = "urlImagem", target = "urlImagem")
    @Mapping(source = "preco", target = "preco")
    @Mapping(source = "desconto", target = "desconto")
    PratoResponseDTO toDto(Prato prato);
}