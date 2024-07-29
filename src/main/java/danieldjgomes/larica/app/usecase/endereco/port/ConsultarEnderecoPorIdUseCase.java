package danieldjgomes.larica.app.usecase.endereco.port;

import danieldjgomes.larica.app.usecase.endereco.response.Endereco;

public interface ConsultarEnderecoPorIdUseCase {

    Endereco consultar(String id);
}
