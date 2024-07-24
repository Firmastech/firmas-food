package danieldjgomes.larica.core.usecases.endereco;

import danieldjgomes.larica.core.endereco.entity.Endereco;

import java.util.Optional;

public interface ConsultarEnderecoPorCepNumeroUseCase {

    Endereco consultar(String cep, String numero);
}
