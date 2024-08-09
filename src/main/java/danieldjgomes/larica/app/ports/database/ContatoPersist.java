package danieldjgomes.larica.app.ports.database;

import danieldjgomes.larica.app.adapter.database.contato.model.ContatoEntity;
import org.springframework.stereotype.Component;

@Component
public interface ContatoPersist {

    ContatoEntity save(ContatoEntity model);
}
