package danieldjgomes.larica.infrastructure.mapper;

import danieldjgomes.larica.core.cardapio.dtos.request.CardapioRequestDTO;
import danieldjgomes.larica.core.cardapio.dtos.response.CardapioResponseDTO;
import danieldjgomes.larica.core.cardapio.dtos.request.CardapioUpdateRequestDTO;
import danieldjgomes.larica.core.cardapio.entity.Cardapio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CardapioMapper {

    CardapioMapper INSTANCE = Mappers.getMapper(CardapioMapper.class);


    @Mapping(source = "restauranteId", target = "restauranteId")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "descricao", target = "descricao")
    Cardapio toEntity(CardapioRequestDTO dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "descricao", target = "descricao")
    CardapioResponseDTO toDto(Cardapio entity);

    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "descricao", target = "descricao")
    void updateCardapioFromDto(CardapioUpdateRequestDTO dto, @MappingTarget Cardapio cardapio);


}
