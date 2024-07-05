package danieldjgomes.larica.infrastructure;

import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface DTOMapper {

    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "endereco.uf", target = "endereco.uf")
    @Mapping(source = "endereco.cep", target = "endereco.cep")
    @Mapping(source = "endereco.rua", target = "endereco.rua")
    @Mapping(source = "endereco.cidade", target = "endereco.cidade")
    @Mapping(source = "endereco.numero", target = "endereco.numero")
    @Mapping(source = "endereco.pontoReferencia", target = "endereco.pontoReferencia")
     Restaurante toRestaurante(CriarRestauranteDTO criarRestauranteDTO);
}
