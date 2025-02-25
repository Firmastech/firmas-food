package danieldjgomes.larica.app.usecase.cardapio;

import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;
import org.antlr.v4.runtime.misc.MultiMap;

import java.util.Optional;


public interface BuscarCardapioAtivoUseCase {

    Optional<CardapioResponse> buscar();

}
