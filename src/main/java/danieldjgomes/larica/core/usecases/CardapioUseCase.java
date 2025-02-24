package danieldjgomes.larica.core.usecases;

import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;
import danieldjgomes.larica.app.usecase.cardapio.request.CardapioRequestDTO;

public interface CardapioUseCase {

    CardapioResponse criarCardapio(CardapioRequestDTO cardapioRequestDTO, UsuarioEntity usuario);

    CardapioResponse atualizarCardapio(String id, CardapioRequestDTO cardapioUpdateRequestDTO);

    void desativarCardapio(String id);


}
