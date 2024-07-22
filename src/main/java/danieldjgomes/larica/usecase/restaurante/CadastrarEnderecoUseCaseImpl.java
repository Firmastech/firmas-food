package danieldjgomes.larica.usecase.restaurante;

import danieldjgomes.larica.adapter.database.endereco.model.EnderecoModel;
import danieldjgomes.larica.adapter.mapper.EnderecoMapper;
import danieldjgomes.larica.core.endereco.entity.Endereco;
import danieldjgomes.larica.core.usecases.endereco.CadastrarEnderecoUseCase;
import danieldjgomes.larica.ports.database.EnderecoPersist;
import danieldjgomes.larica.usecase.restaurante.exceptions.EnderecoInvalidoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CadastrarEnderecoUseCaseImpl implements CadastrarEnderecoUseCase {

    private final EnderecoPersist enderecoPersist;
    private final EnderecoMapper mapper;

    @Override
    public Endereco cadastrar(Endereco endereco) {
        EnderecoModel enderecoModel = mapper.toEntity(endereco);
        enderecoPersist.findByCEPandNumero(endereco.getCep(), endereco.getNumero())
                .ifPresent((endercoEncontrado)->{
                    throw new EnderecoInvalidoException("Endereco ja cadastrado");
                });

        enderecoModel = enderecoPersist.save(enderecoModel);
        return mapper.toEndereco(enderecoModel);
    }
}
