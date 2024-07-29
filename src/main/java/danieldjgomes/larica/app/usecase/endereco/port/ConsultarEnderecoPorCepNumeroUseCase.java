package danieldjgomes.larica.app.usecase.endereco.port;

import danieldjgomes.larica.app.usecase.endereco.response.Endereco;

public interface ConsultarEnderecoPorCepNumeroUseCase {

    Endereco consultar(String cep, String numero);
}
