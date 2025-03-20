package danieldjgomes.larica.infrastructure.mapper;


import danieldjgomes.larica.app.adapter.database.prato.model.PratoEntity;
import danieldjgomes.larica.app.usecase.prato.reqeust.AtualizarPratoRequest;
import danieldjgomes.larica.app.usecase.prato.reqeust.CriarPratoRequest;
import danieldjgomes.larica.app.usecase.prato.response.AtualizarPratoResponse;
import danieldjgomes.larica.app.usecase.prato.response.CriarPratoResponse;
import danieldjgomes.larica.app.usecase.prato.response.PratoResponse;
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
    PratoEntity toEntity(CriarPratoRequest dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "descricao", target = "descricao")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "preco", target = "preco")
    @Mapping(source = "urlImagem", target = "urlImagem")
    @Mapping(source = "porcentagemDesconto", target = "porcentagemDesconto")
    PratoResponse toResponseDTO(PratoEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "descricao", target = "descricao")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "preco", target = "preco")
    @Mapping(source = "urlImagem", target = "urlImagem")
    @Mapping(source = "porcentagemDesconto", target = "porcentagemDesconto")
    void updateEntityFromDTO(AtualizarPratoRequest dto, @MappingTarget PratoEntity entity);

    AtualizarPratoResponse toAtualizarPratoResponse(PratoResponse pratoResponse);

    PratoResponse toPratoResponse(PratoEntity pratoEntity);

    CriarPratoResponse toCriarPratoResponse(PratoEntity prato);
}