package danieldjgomes.larica.usecase.endereco;

import danieldjgomes.larica.adapter.database.endereco.model.EnderecoModel;
import danieldjgomes.larica.adapter.mapper.EnderecoMapper;
import danieldjgomes.larica.core.endereco.entity.Endereco;
import danieldjgomes.larica.core.usecases.endereco.ConsultarEnderecoPorIdUseCase;
import danieldjgomes.larica.core.usecases.endereco.InativarEnderecoUseCase;
import danieldjgomes.larica.ports.database.EnderecoPersist;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InativarEnderecoUseCaseImpl implements InativarEnderecoUseCase {

    private final ConsultarEnderecoPorIdUseCase consultarEnderecoPorIdUseCase;
    private final EnderecoPersist enderecoPersist;
    private final EnderecoMapper enderecoMapper;

    @Override
    public void inativar(String id) {
        Endereco endereco = consultarEnderecoPorIdUseCase.consultar(id);
        EnderecoModel model = enderecoMapper.toModel(endereco);
        enderecoPersist.inativar(model);
    }
}
