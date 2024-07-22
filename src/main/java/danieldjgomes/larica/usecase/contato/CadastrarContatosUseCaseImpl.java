package danieldjgomes.larica.usecase.contato;

import danieldjgomes.larica.adapter.database.contato.model.ContatoModel;
import danieldjgomes.larica.core.usecases.contato.CadastrarContatoUseCase;
import danieldjgomes.larica.ports.database.ContatoPersist;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CadastrarContatosUseCaseImpl implements CadastrarContatoUseCase {

    private ContatoPersist contatoPersist;

    @Override
    public List<ContatoModel> cadastrar(final List<ContatoModel> contatoModels, final String idRestaurante) {
        List<ContatoModel> cadastrados = new ArrayList<>();

        contatoModels.forEach((model)->{
            model.setRestauranteId(idRestaurante);
            ContatoModel cadastrado  = contatoPersist.save(model);
            cadastrados.add(cadastrado);
            }
        );

        return cadastrados;
    }
}
