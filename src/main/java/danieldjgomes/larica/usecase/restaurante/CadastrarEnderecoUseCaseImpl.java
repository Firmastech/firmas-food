package danieldjgomes.larica.usecase.restaurante;

import danieldjgomes.larica.adapter.database.endereco.model.EnderecoModel;
import danieldjgomes.larica.adapter.mapper.EnderecoMapper;
import danieldjgomes.larica.core.endereco.entity.Endereco;
import danieldjgomes.larica.core.endereco.contract.EnderecoRepository;
import danieldjgomes.larica.core.usecases.endereco.CadastrarEnderecoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CadastrarEnderecoUseCaseImpl implements CadastrarEnderecoUseCase {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoMapper mapper;

    @Override
    public Endereco cadastrar(Endereco endereco) {
        EnderecoModel enderecoModel = mapper.toEntity(endereco);
        enderecoModel = enderecoRepository.save(enderecoModel);
        return mapper.toEndereco(enderecoModel);
    }
}
