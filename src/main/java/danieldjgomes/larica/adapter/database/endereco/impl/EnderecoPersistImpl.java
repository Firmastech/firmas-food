package danieldjgomes.larica.adapter.database.endereco.impl;

import danieldjgomes.larica.adapter.database.endereco.repository.EnderecoRepository;
import danieldjgomes.larica.adapter.database.endereco.model.EnderecoModel;
import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;
import danieldjgomes.larica.ports.database.EnderecoPersist;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

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
        return enderecoRepository.findByCepAndNumero(CEP, numero);
    }

    @Override
    public EnderecoModel save(EnderecoModel endereco) {
        endereco.setCriadoEm(LocalDateTime.now());
        endereco.setAtivo(true);
        return enderecoRepository.save(endereco);
    }

    @Override
    public EnderecoModel update(EnderecoModel endereco) {
        endereco.setAtualizadoEm(LocalDateTime.now());
        return enderecoRepository.save(endereco);
    }

    @Override
    public void inativar(EnderecoModel endereco) {
        endereco.setDeletadoEm(LocalDateTime.now());
        endereco.setAtivo(false);
        enderecoRepository.save(endereco);
    }

}
