package danieldjgomes.larica.ports.database;

import danieldjgomes.larica.adapter.database.contato.model.ContatoModel;
import org.springframework.stereotype.Component;

@Component
public interface ContatoPersist {

    ContatoModel save(ContatoModel model);
}
