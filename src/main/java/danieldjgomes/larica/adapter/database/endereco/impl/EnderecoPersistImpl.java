package danieldjgomes.larica.adapter.database.endereco.impl;

import danieldjgomes.larica.adapter.database.endereco.repository.EnderecoRepository;
import danieldjgomes.larica.adapter.database.endereco.model.EnderecoModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@AllArgsConstructor
public class EnderecoPersistImpl implements danieldjgomes.larica.core.endereco.contract.EnderecoRepository {

    private EnderecoRepository enderecoRepository;

    @Override
    public EnderecoModel save(EnderecoModel endereco) {
        endereco.setId(UUID.randomUUID().toString());
        return enderecoRepository.save(endereco);
    }

}
