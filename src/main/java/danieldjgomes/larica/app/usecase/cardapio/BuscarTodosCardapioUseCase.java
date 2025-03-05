package danieldjgomes.larica.app.usecase.cardapio;

import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BuscarTodosCardapioUseCase {

    Page<CardapioResponse> buscarTodosCardapios(Pageable cardapioId);
}
