package danieldjgomes.larica.app.adapter.mapper;

import danieldjgomes.larica.app.adapter.database.endereco.model.EnderecoEntity;
import danieldjgomes.larica.app.usecase.endereco.response.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "rua", target = "rua")
    @Mapping(source = "numero", target = "numero")
    @Mapping(source = "cep", target = "cep")
    @Mapping(source = "cidade", target = "cidade")
    @Mapping(source = "uf", target = "uf")
    @Mapping(source = "pontoReferencia", target = "pontoReferencia")
    EnderecoEntity toModel(Endereco endereco);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "rua", target = "rua")
    @Mapping(source = "numero", target = "numero")
    @Mapping(source = "cep", target = "cep")
    @Mapping(source = "cidade", target = "cidade")
    @Mapping(source = "uf", target = "uf")
    @Mapping(source = "pontoReferencia", target = "pontoReferencia")
    Endereco toEndereco(EnderecoEntity endereco);
}
