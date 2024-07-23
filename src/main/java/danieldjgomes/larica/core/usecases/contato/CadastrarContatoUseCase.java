package danieldjgomes.larica.core.usecases.contato;

import danieldjgomes.larica.adapter.database.contato.model.ContatoModel;

import java.util.List;

public interface CadastrarContatoUseCase {

    ContatoModel cadastrar(ContatoModel contatoModel);
}
