package danieldjgomes.larica.adapter.database.contato.impl;

import danieldjgomes.larica.adapter.database.contato.model.ContatoModel;
import danieldjgomes.larica.adapter.database.contato.repository.ContatoRepository;
import danieldjgomes.larica.ports.database.ContatoPersist;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
@AllArgsConstructor
public class ContatoPersistImpl implements ContatoPersist {

    private final ContatoRepository contatoRepository;

    @Override
    public ContatoModel save(ContatoModel contatoModel) {
        contatoModel.setCriadoEm(new Date());
        contatoModel.setAtivo(true);
        return contatoRepository.save(contatoModel);
    }
}
