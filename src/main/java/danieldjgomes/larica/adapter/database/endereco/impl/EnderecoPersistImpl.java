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
    public Optional<EnderecoModel> findByCEPandNumero(String CEP,Integer numero) {
        return enderecoRepository.findByCepAndNumero(CEP, numero);
    }

    @Override
    public EnderecoModel save(EnderecoModel endereco) {
        endereco.setCriadoEm(LocalDateTime.now());
        endereco.setAtivo(true);
        return enderecoRepository.save(endereco);
    }

}
