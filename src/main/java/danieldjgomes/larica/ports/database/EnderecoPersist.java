package danieldjgomes.larica.ports.database;

import danieldjgomes.larica.adapter.database.endereco.model.EnderecoModel;
import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;

import java.util.Optional;

public interface EnderecoPersist {

    Optional<EnderecoModel> findById(String id);
    Optional<EnderecoModel> findByCEPandNumero(String cep,String numero);
    EnderecoModel save(EnderecoModel endereco);
    EnderecoModel update(EnderecoModel endereco);
    void inativar(EnderecoModel endereco);

}
