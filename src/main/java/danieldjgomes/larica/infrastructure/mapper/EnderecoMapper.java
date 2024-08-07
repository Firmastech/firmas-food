package danieldjgomes.larica.infrastructure.mapper;

import danieldjgomes.larica.app.usecase.endereco.request.AtualizarEnderecoRequest;
import danieldjgomes.larica.app.usecase.endereco.request.CadastrarEnderecoRequest;
import danieldjgomes.larica.app.usecase.endereco.response.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

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
