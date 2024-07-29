package danieldjgomes.larica.app.usecase.cardapioPrato;

import java.util.List;

public interface AddPratosToCardapioUseCase {

    void addPratosToCardapio(String cardapioId, List<String> pratoIds);

}
