package danieldjgomes.larica.infrastructure.mapper;

import danieldjgomes.larica.app.usecase.endereco.response.Endereco;
import danieldjgomes.larica.app.usecase.restaurante.response.Restaurante;
import danieldjgomes.larica.app.usecase.endereco.request.AtualizarEnderecoRequest;
import danieldjgomes.larica.app.usecase.restaurante.request.AtualizarRestauranteRequest;
import danieldjgomes.larica.app.usecase.endereco.request.CadastrarEnderecoRequest;
import danieldjgomes.larica.app.usecase.restaurante.request.CriarRestauranteRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DTOMapper {

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

    @Mapping(source = "rua", target = "rua")
    @Mapping(source = "numero", target = "numero")
    @Mapping(source = "cep", target = "cep")
    @Mapping(source = "cidade", target = "cidade")
    @Mapping(source = "uf", target = "uf")
    @Mapping(source = "pontoReferencia", target = "pontoReferencia")
    Endereco toEndereco(CadastrarEnderecoRequest endereco);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "rua", target = "rua")
    @Mapping(source = "numero", target = "numero")
    @Mapping(source = "cep", target = "cep")
    @Mapping(source = "cidade", target = "cidade")
    @Mapping(source = "uf", target = "uf")
    @Mapping(source = "pontoReferencia", target = "pontoReferencia")
    Endereco toEndereco(AtualizarEnderecoRequest endereco);

}
