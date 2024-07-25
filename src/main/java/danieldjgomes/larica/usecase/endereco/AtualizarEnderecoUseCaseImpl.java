package danieldjgomes.larica.usecase.endereco;

import danieldjgomes.larica.adapter.database.endereco.model.EnderecoModel;
import danieldjgomes.larica.adapter.mapper.EnderecoMapper;
import danieldjgomes.larica.core.endereco.entity.Endereco;
import danieldjgomes.larica.core.usecases.endereco.AtualizarEnderecoUseCase;
import danieldjgomes.larica.core.usecases.endereco.ConsultarEnderecoPorCepNumeroUseCase;
import danieldjgomes.larica.core.usecases.endereco.ConsultarEnderecoPorIdUseCase;
import danieldjgomes.larica.ports.database.EnderecoPersist;
import danieldjgomes.larica.usecase.endereco.exceptions.EnderecoInvalidoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class AtualizarEnderecoUseCaseImpl implements AtualizarEnderecoUseCase {

    private ConsultarEnderecoPorIdUseCase consultarEnderecoPorIdUseCase;
    private ConsultarEnderecoPorCepNumeroUseCase consultarEnderecoPorCepNumeroUseCase;
    private EnderecoPersist enderecoPersist;
    private EnderecoMapper mapper;

    @Override
    public Endereco update(Endereco endereco) {
        final String id = endereco.getId();

        Optional.ofNullable(
                consultarEnderecoPorCepNumeroUseCase.consultar(endereco.getCep(), endereco.getNumero())
        ).ifPresent((object)-> {throw new EnderecoInvalidoException("Endereco Ja cadastrado");});

        final Endereco enderecoAtual = consultarEnderecoPorIdUseCase.consultar(id);
        Optional.ofNullable(endereco.getUf()).ifPresent(enderecoAtual::setUf);
        Optional.ofNullable(endereco.getCep()).ifPresent(enderecoAtual::setCep);
        Optional.ofNullable(endereco.getRua()).ifPresent(enderecoAtual::setRua);
        Optional.ofNullable(endereco.getCidade()).ifPresent(enderecoAtual::setCidade);
        Optional.ofNullable(endereco.getPontoReferencia()).ifPresent(enderecoAtual::setPontoReferencia);
        Optional.ofNullable(endereco.getNumero()).ifPresent(enderecoAtual::setNumero);

        final EnderecoModel model = mapper.toModel(enderecoAtual);
        final EnderecoModel enderecoAtualizado = enderecoPersist.update(model);
        return mapper.toEndereco(enderecoAtualizado);
    }
}
