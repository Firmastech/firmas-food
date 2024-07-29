package danieldjgomes.larica.app.usecase.endereco;


import danieldjgomes.larica.app.adapter.database.endereco.model.EnderecoEntity;
import danieldjgomes.larica.app.adapter.mapper.EnderecoMapper;
import danieldjgomes.larica.app.usecase.endereco.exceptions.EnderecoNaoEncontradoException;
import danieldjgomes.larica.app.usecase.endereco.response.Endereco;
import danieldjgomes.larica.app.usecase.endereco.port.ConsultarEnderecoPorIdUseCase;
import danieldjgomes.larica.app.ports.database.EnderecoPersist;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConsultarEnderecoPorIdUseCaseImpl implements ConsultarEnderecoPorIdUseCase {

    private final EnderecoPersist enderecoPersist;
    private final EnderecoMapper mapper;

    @Override
    public Endereco consultar(String id) {
        EnderecoEntity enderecoEntity = enderecoPersist.findById(id).orElseThrow(
                ()-> new EnderecoNaoEncontradoException("Endereco nao encontrado")
        );

        return mapper.toEndereco(enderecoEntity);
    }
}
