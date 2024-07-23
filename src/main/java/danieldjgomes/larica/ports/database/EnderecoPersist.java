package danieldjgomes.larica.ports.database;

import danieldjgomes.larica.adapter.database.endereco.model.EnderecoModel;
import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;

import java.util.Optional;

public interface EnderecoPersist {

    Optional<EnderecoModel> findByCEPandNumero(String cep,String numero);
    EnderecoModel save(EnderecoModel endereco);
}
