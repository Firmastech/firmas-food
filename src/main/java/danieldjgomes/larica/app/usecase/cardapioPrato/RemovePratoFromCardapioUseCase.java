package danieldjgomes.larica.app.usecase.cardapioPrato;

import java.util.List;

public interface RemovePratoFromCardapioUseCase {

    void desativar(String cardapioId, List<String> pratoIds);

}
