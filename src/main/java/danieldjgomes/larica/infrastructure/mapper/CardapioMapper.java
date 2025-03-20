package danieldjgomes.larica.infrastructure.mapper;

import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.adapter.database.categoria.model.CategoriaEntity;
import danieldjgomes.larica.app.usecase.cardapio.response.AtualizarCardapioResponse;
import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;
import danieldjgomes.larica.app.usecase.cardapio.request.CardapioRequestDTO;
import danieldjgomes.larica.app.usecase.cardapio.request.CriarCardapioRequest;
import danieldjgomes.larica.app.usecase.categoria.response.CategoriaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CardapioMapper {

    CardapioEntity toEntity(CardapioRequestDTO dto);


    @Mapping(target = "categorias", source = "categorias")
    CardapioResponse toCardapioResponse(CardapioEntity entity);

    CategoriaResponse toCategoriaResponse(CategoriaEntity entity);

    AtualizarCardapioResponse updateCardapioFromDto(CardapioEntity cardapioEntity);

    CardapioEntity toEntity(CriarCardapioRequest dto);


}
