package danieldjgomes.larica.core.usecases;

import danieldjgomes.larica.core.cardapio.dtos.request.CardapioRequestDTO;
import danieldjgomes.larica.core.cardapio.dtos.request.CardapioUpdateRequestDTO;
import danieldjgomes.larica.core.cardapio.dtos.response.CardapioResponseDTO;

public interface CardapioUseCase {

    CardapioResponseDTO criarCardapio(CardapioRequestDTO cardapioRequestDTO);

    CardapioResponseDTO atualizarCardapio(String id, CardapioUpdateRequestDTO cardapioUpdateRequestDTO);

    void desativarCardapio(String id);


}
