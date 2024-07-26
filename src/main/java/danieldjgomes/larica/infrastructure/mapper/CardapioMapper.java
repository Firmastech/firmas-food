package danieldjgomes.larica.infrastructure.mapper;

import danieldjgomes.larica.app.usecase.cardapio.request.CriarCardapioRequest;
import danieldjgomes.larica.app.usecase.cardapio.response.AtualizarCardapioResponse;
import danieldjgomes.larica.app.usecase.cardapio.response.ResumoCardapioResponse;
import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;
import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioResumidoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CardapioMapper {

    CardapioMapper INSTANCE = Mappers.getMapper(CardapioMapper.class);


    @Mapping(source = "restauranteId", target = "restaurante")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "descricao", target = "descricao")
    CardapioEntity toEntity(CriarCardapioRequest dto);

    CardapioResponse criarCardapiotoResponse(CardapioEntity entity);
    AtualizarCardapioResponse atualizarCardapiotoResponse(CardapioEntity entity);

    ResumoCardapioResponse toResponse(CardapioResumidoEntity cardapios);


    CardapioResponse toResponse(CardapioEntity cardapio);

    AtualizarCardapioResponse toAtualizarCardapioResponse(CardapioResponse cardapioResponse);
}
