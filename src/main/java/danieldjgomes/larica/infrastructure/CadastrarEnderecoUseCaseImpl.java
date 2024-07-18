package danieldjgomes.larica.infrastructure;

import danieldjgomes.larica.core.endereco.entity.Endereco;
import danieldjgomes.larica.core.restaurante.contract.EnderecoRepository;
import danieldjgomes.larica.core.usecases.endereco.CadastrarEnderecoUseCase;
import danieldjgomes.larica.dataprovider.repository.entity.EnderecoEntity;
import danieldjgomes.larica.dataprovider.repository.mapper.EnderecoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CadastrarEnderecoUseCaseImpl implements CadastrarEnderecoUseCase {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoMapper mapper;

    @Override
    public Endereco cadastrar(Endereco endereco) {
        EnderecoEntity enderecoEntity = mapper.toEntity(endereco);
        enderecoEntity = enderecoRepository.save(enderecoEntity);
        return mapper.toEndereco(enderecoEntity);
    }
}
