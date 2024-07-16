package danieldjgomes.larica.infrastructure.mapper;

import danieldjgomes.larica.core.desconto.dtos.DescontoRequestDTO;
import danieldjgomes.larica.core.desconto.dtos.DescontoResponseDTO;
import danieldjgomes.larica.core.desconto.entity.Desconto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DescontoMapper {
    DescontoMapper INSTANCE = Mappers.getMapper(DescontoMapper.class);

    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "descricao", target = "descricao")
    @Mapping(source = "porcentagemDesconto", target = "porcentagemDesconto")
    Desconto toEntity(DescontoRequestDTO dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "descricao", target = "descricao")
    @Mapping(source = "porcentagemDesconto", target = "porcentagemDesconto")
    DescontoResponseDTO toDto(Desconto desconto);
}