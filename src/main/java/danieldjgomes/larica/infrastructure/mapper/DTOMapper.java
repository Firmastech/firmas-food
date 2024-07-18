package danieldjgomes.larica.infrastructure.mapper;

import danieldjgomes.larica.core.endereco.entity.Endereco;
import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.infrastructure.dto.restaurante.request.AtualizarRestauranteRequestDTO;
import danieldjgomes.larica.infrastructure.dto.restaurante.request.CadastrarEnderecoRequest;
import danieldjgomes.larica.infrastructure.dto.restaurante.request.CriarRestauranteRequestDTO;
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
     Restaurante toRestaurante(CriarRestauranteRequestDTO criarRestauranteRequestDTO);

    @Mapping(source = "id",target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "tempoEstimadoDeEntrega", target = "tempoEstimadoDeEntrega")
    @Mapping(source = "statusFuncionamento", target = "statusFuncionamento")
    Restaurante toRestaurante(AtualizarRestauranteRequestDTO dto);

    @Mapping(source = "rua", target = "rua")
    @Mapping(source = "numero", target = "numero")
    @Mapping(source = "cep", target = "cep")
    @Mapping(source = "cidade", target = "cidade")
    @Mapping(source = "uf", target = "uf")
    @Mapping(source = "pontoReferencia", target = "pontoReferencia")
    Endereco toEndereco(CadastrarEnderecoRequest endereco);
}
