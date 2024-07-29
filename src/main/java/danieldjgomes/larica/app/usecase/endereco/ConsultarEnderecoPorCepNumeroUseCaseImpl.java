package danieldjgomes.larica.app.usecase.endereco;

import danieldjgomes.larica.app.adapter.database.endereco.model.EnderecoEntity;
import danieldjgomes.larica.app.adapter.mapper.EnderecoMapper;
import danieldjgomes.larica.app.usecase.endereco.response.Endereco;
import danieldjgomes.larica.app.usecase.endereco.port.ConsultarEnderecoPorCepNumeroUseCase;
import danieldjgomes.larica.app.ports.database.EnderecoPersist;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConsultarEnderecoPorCepNumeroUseCaseImpl implements ConsultarEnderecoPorCepNumeroUseCase {

    private EnderecoPersist enderecoPersist;
    private EnderecoMapper mapper;

    @Override
    public Endereco consultar(String CEP, String numero) {
        EnderecoEntity endereco = enderecoPersist.findByCEPandNumero(CEP,numero).orElse(null);
        return mapper.toEndereco(endereco);
    }
}
