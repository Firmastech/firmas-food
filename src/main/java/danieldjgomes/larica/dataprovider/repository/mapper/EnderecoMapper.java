package danieldjgomes.larica.dataprovider.repository.mapper;

import danieldjgomes.larica.core.endereco.entity.Endereco;
import danieldjgomes.larica.dataprovider.repository.entity.EnderecoEntity;
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
    EnderecoEntity toEntity(Endereco endereco);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "rua", target = "rua")
    @Mapping(source = "numero", target = "numero")
    @Mapping(source = "cep", target = "cep")
    @Mapping(source = "cidade", target = "cidade")
    @Mapping(source = "uf", target = "uf")
    @Mapping(source = "pontoReferencia", target = "pontoReferencia")
    Endereco toEndereco(EnderecoEntity endereco);
}
