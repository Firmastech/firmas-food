package danieldjgomes.larica.infrastructure.mapper;

import danieldjgomes.larica.core.cardapio.dtos.CardapioRequestDTO;
import danieldjgomes.larica.core.cardapio.dtos.CardapioResponseDTO;
import danieldjgomes.larica.core.cardapio.entity.Cardapio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CardapioMapper {

    CardapioMapper INSTANCE = Mappers.getMapper(CardapioMapper.class);


    @Mapping(source = "pratoId", target = "pratoId")
    @Mapping(source = "tipoCulinariaId", target = "tipoCulinariaId")
    Cardapio toEntity(CardapioRequestDTO dto);

    @Mapping(source = "cardapioId", target = "cardapioId")
    @Mapping(source = "pratoId", target = "prato")
    @Mapping(source = "tipoCulinariaId", target = "tipoCulinaria")
    CardapioResponseDTO toDto(Cardapio entity);


}
