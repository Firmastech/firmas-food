package danieldjgomes.larica.app.usecase.contato.port;

import danieldjgomes.larica.app.adapter.database.contato.model.ContatoEntity;

public interface CadastrarContatoUseCase {

    ContatoEntity cadastrar(ContatoEntity contatoEntity);
}
