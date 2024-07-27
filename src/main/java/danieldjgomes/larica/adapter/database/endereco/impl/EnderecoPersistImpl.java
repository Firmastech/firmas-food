package danieldjgomes.larica.adapter.database.endereco.impl;

import danieldjgomes.larica.adapter.database.endereco.repository.EnderecoRepository;
import danieldjgomes.larica.adapter.database.endereco.model.EnderecoModel;
import danieldjgomes.larica.ports.database.EnderecoPersist;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@AllArgsConstructor
public class EnderecoPersistImpl implements EnderecoPersist {

    private EnderecoRepository enderecoRepository;

    @Override
    public Optional<EnderecoModel> findById(String id) {
        return enderecoRepository.findById(id);
    }

    @Override
    public Optional<EnderecoModel> findByCEPandNumero(String CEP,String numero) {
        return enderecoRepository.findByCepAndNumeroAndAtivo(CEP, numero,true);
    }

    @Override
    public EnderecoModel save(EnderecoModel endereco) {
        endereco.setCriadoEm(new Date());
        endereco.setAtivo(true);
        return enderecoRepository.save(endereco);
    }

    @Override
    public EnderecoModel update(EnderecoModel endereco) {
        endereco.setAtualizadoEm(new Date());
        return enderecoRepository.save(endereco);
    }

    @Override
    public void inativar(EnderecoModel endereco) {
        endereco.setDeletadoEm(new Date());
        endereco.setAtivo(false);
        enderecoRepository.save(endereco);
    }

}
