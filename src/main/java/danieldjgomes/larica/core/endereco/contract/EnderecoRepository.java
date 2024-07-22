package danieldjgomes.larica.core.endereco.contract;

import danieldjgomes.larica.adapter.database.endereco.model.EnderecoModel;

public interface EnderecoRepository {

    EnderecoModel save(EnderecoModel endereco);
}
