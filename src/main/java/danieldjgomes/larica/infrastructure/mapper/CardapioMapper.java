package danieldjgomes.larica.infrastructure.mapper;

import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.usecase.cardapio.response.AtualizarCardapioResponse;
import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;
import danieldjgomes.larica.app.usecase.cardapio.request.CardapioRequestDTO;
import danieldjgomes.larica.app.usecase.cardapio.request.CriarCardapioRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CardapioMapper {

    CardapioMapper INSTANCE = Mappers.getMapper(CardapioMapper.class);


    CardapioEntity toEntity(CardapioRequestDTO dto);

    CardapioResponse toCardapioResponse(CardapioEntity entity);

    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "descricao", target = "descricao")
    AtualizarCardapioResponse updateCardapioFromDto(CardapioEntity cardapioEntity);

    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "descricao", target = "descricao")
    CardapioEntity toEntity(CriarCardapioRequest dto);


}
