package danieldjgomes.larica.app.ports.database;

import danieldjgomes.larica.app.adapter.database.endereco.model.EnderecoEntity;

import java.util.Optional;

public interface EnderecoPersist {

    Optional<EnderecoEntity> findById(String id);
    Optional<EnderecoEntity> findByCEPandNumero(String cep, String numero);
    EnderecoEntity save(EnderecoEntity endereco);
    EnderecoEntity update(EnderecoEntity endereco);
    void inativar(EnderecoEntity endereco);

}
