package danieldjgomes.larica.core.usecases.contato;

import danieldjgomes.larica.adapter.database.contato.model.ContatoModel;

import java.util.List;

public interface CadastrarContatoUseCase {

    List<ContatoModel> cadastrar(List<ContatoModel> contatoModels, String idRestaurante);
}
