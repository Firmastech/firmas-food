package danieldjgomes.larica.app.adapter.mapper;

import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;
import danieldjgomes.larica.app.usecase.restaurante.request.AtualizarRestauranteRequest;
import danieldjgomes.larica.app.usecase.restaurante.request.CriarRestauranteRequest;
import danieldjgomes.larica.app.usecase.restaurante.response.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestauranteMapper {

    @Mapping(source = "id",target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "tempoEstimadoDeEntrega", target = "tempoEstimadoDeEntrega")
    @Mapping(source = "statusFuncionamento", target = "statusFuncionamento")
    RestauranteEntity toEntity(Restaurante restaurante);

    @Mapping(source = "id",target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "tempoEstimadoDeEntrega", target = "tempoEstimadoDeEntrega")
    @Mapping(source = "statusFuncionamento", target = "statusFuncionamento")
    Restaurante toRestaurante(RestauranteEntity entity);


    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "tempoEstimadoDeEntrega", target = "tempoEstimadoDeEntrega")
    @Mapping(source = "statusFuncionamento", target = "statusFuncionamento")
    @Mapping(source = "endereco.uf", target = "endereco.uf")
    @Mapping(source = "endereco.cep", target = "endereco.cep")
    @Mapping(source = "endereco.rua", target = "endereco.rua")
    @Mapping(source = "endereco.cidade", target = "endereco.cidade")
    @Mapping(source = "endereco.numero", target = "endereco.numero")
    @Mapping(source = "endereco.pontoReferencia", target = "endereco.pontoReferencia")
    Restaurante toRestaurante(CriarRestauranteRequest criarRestauranteRequest);

    @Mapping(source = "id",target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "tempoEstimadoDeEntrega", target = "tempoEstimadoDeEntrega")
    @Mapping(source = "statusFuncionamento", target = "statusFuncionamento")
    Restaurante toRestaurante(AtualizarRestauranteRequest dto);

}
