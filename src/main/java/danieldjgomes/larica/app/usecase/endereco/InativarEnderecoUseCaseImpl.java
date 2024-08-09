package danieldjgomes.larica.app.usecase.endereco;

import danieldjgomes.larica.app.adapter.database.endereco.model.EnderecoEntity;
import danieldjgomes.larica.app.adapter.mapper.EnderecoMapper;
import danieldjgomes.larica.app.usecase.endereco.response.Endereco;
import danieldjgomes.larica.app.usecase.endereco.port.ConsultarEnderecoPorIdUseCase;
import danieldjgomes.larica.app.usecase.endereco.port.InativarEnderecoUseCase;
import danieldjgomes.larica.app.ports.database.EnderecoPersist;
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
        EnderecoEntity model = enderecoMapper.toModel(endereco);
        enderecoPersist.inativar(model);
    }
}
