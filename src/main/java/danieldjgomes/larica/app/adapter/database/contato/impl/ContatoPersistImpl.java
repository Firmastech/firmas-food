package danieldjgomes.larica.app.adapter.database.contato.impl;

import danieldjgomes.larica.app.adapter.database.contato.model.ContatoEntity;
import danieldjgomes.larica.app.adapter.database.contato.repository.ContatoRepository;
import danieldjgomes.larica.app.ports.database.ContatoPersist;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class ContatoPersistImpl implements ContatoPersist {

    private final ContatoRepository contatoRepository;

    @Override
    public ContatoEntity save(ContatoEntity contatoEntity) {
        contatoEntity.setCriadoEm(LocalDateTime.now());
        contatoEntity.setAtivo(true);
        return contatoRepository.save(contatoEntity);
    }
}
