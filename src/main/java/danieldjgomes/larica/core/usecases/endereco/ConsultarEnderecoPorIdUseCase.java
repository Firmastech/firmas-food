package danieldjgomes.larica.core.usecases.endereco;

import danieldjgomes.larica.core.endereco.entity.Endereco;

public interface ConsultarEnderecoPorIdUseCase {

    Endereco consultar(String id);
}
