package danieldjgomes.larica.infrastructure.mapper;

import danieldjgomes.larica.core.prato.dtos.PratoRequestDTO;
import danieldjgomes.larica.core.prato.dtos.PratoResponseDTO;
import danieldjgomes.larica.core.prato.entity.PratoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface PratoMapper {

    PratoMapper INSTANCE = Mappers.getMapper(PratoMapper.class);

    @Mapping(source = "descricao", target = "descricao")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "preco", target = "preco")
    @Mapping(source = "urlImagem", target = "urlImagem")
    @Mapping(source = "porcentagemDesconto", target = "porcentagemDesconto")
    PratoEntity toEntity(PratoRequestDTO dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "descricao", target = "descricao")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "preco", target = "preco")
    @Mapping(source = "urlImagem", target = "urlImagem")
    @Mapping(source = "porcentagemDesconto", target = "porcentagemDesconto")
    PratoResponseDTO toResponseDTO(PratoEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "descricao", target = "descricao")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "preco", target = "preco")
    @Mapping(source = "urlImagem", target = "urlImagem")
    @Mapping(source = "porcentagemDesconto", target = "porcentagemDesconto")
    void updateEntityFromDTO(PratoRequestDTO dto, @MappingTarget PratoEntity entity);

}