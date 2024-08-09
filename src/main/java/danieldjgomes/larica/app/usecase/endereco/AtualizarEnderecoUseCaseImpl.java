package danieldjgomes.larica.app.usecase.endereco;

import danieldjgomes.larica.app.adapter.database.endereco.model.EnderecoEntity;
import danieldjgomes.larica.app.adapter.mapper.EnderecoMapper;
import danieldjgomes.larica.app.usecase.endereco.response.Endereco;
import danieldjgomes.larica.app.usecase.endereco.port.AtualizarEnderecoUseCase;
import danieldjgomes.larica.app.usecase.endereco.port.ConsultarEnderecoPorCepNumeroUseCase;
import danieldjgomes.larica.app.usecase.endereco.port.ConsultarEnderecoPorIdUseCase;
import danieldjgomes.larica.app.ports.database.EnderecoPersist;
import danieldjgomes.larica.app.usecase.endereco.exceptions.EnderecoInvalidoException;
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

        final EnderecoEntity model = mapper.toModel(enderecoAtual);
        final EnderecoEntity enderecoAtualizado = enderecoPersist.update(model);
        return mapper.toEndereco(enderecoAtualizado);
    }
}
