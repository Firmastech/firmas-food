package danieldjgomes.larica.usecase.endereco;

import danieldjgomes.larica.adapter.database.endereco.model.EnderecoModel;
import danieldjgomes.larica.adapter.mapper.EnderecoMapper;
import danieldjgomes.larica.core.endereco.entity.Endereco;
import danieldjgomes.larica.core.usecases.endereco.ConsultarEnderecoPorCepNumeroUseCase;
import danieldjgomes.larica.ports.database.EnderecoPersist;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConsultarEnderecoPorCepNumeroUseCaseImpl implements ConsultarEnderecoPorCepNumeroUseCase {

    private EnderecoPersist enderecoPersist;
    private EnderecoMapper mapper;

    @Override
    public Endereco consultar(String CEP, String numero) {
        EnderecoModel endereco = enderecoPersist.findByCEPandNumero(CEP,numero).orElse(null);
        return mapper.toEndereco(endereco);
    }
}
