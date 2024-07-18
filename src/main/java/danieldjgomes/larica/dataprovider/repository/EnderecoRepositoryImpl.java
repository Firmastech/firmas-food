package danieldjgomes.larica.dataprovider.repository;

import danieldjgomes.larica.core.restaurante.contract.EnderecoRepository;
import danieldjgomes.larica.dataprovider.repository.entity.EnderecoEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@AllArgsConstructor
public class EnderecoRepositoryImpl implements EnderecoRepository {

    private EnderecoDao enderecoDao;

    @Override
    public EnderecoEntity save(EnderecoEntity endereco) {
        endereco.setId(UUID.randomUUID().toString());
        return enderecoDao.save(endereco);
    }

}
