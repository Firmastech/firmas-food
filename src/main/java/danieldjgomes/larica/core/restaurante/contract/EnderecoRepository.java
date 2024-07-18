package danieldjgomes.larica.core.restaurante.contract;

import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.dataprovider.repository.entity.EnderecoEntity;
import danieldjgomes.larica.dataprovider.repository.entity.RestauranteEntity;

public interface EnderecoRepository {

    EnderecoEntity save(EnderecoEntity endereco);
}
