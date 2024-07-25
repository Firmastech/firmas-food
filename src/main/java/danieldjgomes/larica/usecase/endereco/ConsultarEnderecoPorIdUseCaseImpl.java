package danieldjgomes.larica.usecase.endereco;


import danieldjgomes.larica.adapter.database.endereco.model.EnderecoModel;
import danieldjgomes.larica.adapter.mapper.EnderecoMapper;
import danieldjgomes.larica.core.endereco.entity.Endereco;
import danieldjgomes.larica.core.usecases.endereco.ConsultarEnderecoPorIdUseCase;
import danieldjgomes.larica.ports.database.EnderecoPersist;

import danieldjgomes.larica.usecase.endereco.exceptions.EnderecoNaoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConsultarEnderecoPorIdUseCaseImpl implements ConsultarEnderecoPorIdUseCase {

    private final EnderecoPersist enderecoPersist;
    private final EnderecoMapper mapper;

    @Override
    public Endereco consultar(String id) {
        EnderecoModel enderecoModel= enderecoPersist.findById(id).orElseThrow(
                ()-> new EnderecoNaoEncontradoException("Endereco nao encontrado")
        );

        return mapper.toEndereco(enderecoModel);
    }
}
