package danieldjgomes.larica.infrastructure.mapper;

import danieldjgomes.larica.app.usecase.categoria.response.BuscarDetalhesCategoriaResponse;
import danieldjgomes.larica.app.usecase.categoria.response.CategoriaResponse;
import danieldjgomes.larica.app.usecase.categoria.response.AtualizarCategoriaResponse;
import danieldjgomes.larica.app.usecase.categoria.request.CriarCategoriaRequest;
import danieldjgomes.larica.app.adapter.database.categoria.model.CategoriaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    @Mapping(target = "id", ignore = true)
    CategoriaEntity toEntity(CriarCategoriaRequest dto);

    CategoriaResponse toResponseDTO(CategoriaEntity entity);

    BuscarDetalhesCategoriaResponse toDetalhesCategoria(CategoriaEntity entity);

    AtualizarCategoriaResponse updateEntityFromDTO(CategoriaEntity entity);
}
