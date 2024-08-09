package danieldjgomes.larica.app.adapter.database.endereco.impl;

import danieldjgomes.larica.app.adapter.database.endereco.model.EnderecoEntity;
import danieldjgomes.larica.app.adapter.database.endereco.repository.EnderecoRepository;
import danieldjgomes.larica.app.ports.database.EnderecoPersist;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@AllArgsConstructor
public class EnderecoPersistImpl implements EnderecoPersist {

    private EnderecoRepository enderecoRepository;

    @Override
    public Optional<EnderecoEntity> findById(String id) {
        return enderecoRepository.findById(id);
    }

    @Override
    public Optional<EnderecoEntity> findByCEPandNumero(String CEP, String numero) {
        return enderecoRepository.findByCepAndNumeroAndAtivo(CEP, numero,true);
    }

    @Override
    public EnderecoEntity save(EnderecoEntity endereco) {
        endereco.setCriadoEm(new Date());
        endereco.setAtivo(true);
        return enderecoRepository.save(endereco);
    }

    @Override
    public EnderecoEntity update(EnderecoEntity endereco) {
        endereco.setAtualizadoEm(new Date());
        return enderecoRepository.save(endereco);
    }

    @Override
    public void inativar(EnderecoEntity endereco) {
        endereco.setDeletadoEm(new Date());
        endereco.setAtivo(false);
        enderecoRepository.save(endereco);
    }

}
