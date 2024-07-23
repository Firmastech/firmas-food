package danieldjgomes.larica.core.usecases;

import danieldjgomes.larica.core.cardapio.dtos.request.CardapioRequestDTO;
import danieldjgomes.larica.core.cardapio.dtos.request.CardapioUpdateRequestDTO;
import danieldjgomes.larica.core.cardapio.dtos.response.CardapioResponseDTO;

import java.util.List;

public interface CardapioUseCase {

    CardapioResponseDTO criarCardapio(CardapioRequestDTO cardapioRequestDTO);

    void adicionarPratos(String cardapioId, List<String> pratoIds);

    void removerPrato (String cardapioId, String pratoId);

    CardapioResponseDTO atualizarCardapio(String id, CardapioUpdateRequestDTO cardapioUpdateRequestDTO);

    void desativarCardapio(String id);


}
