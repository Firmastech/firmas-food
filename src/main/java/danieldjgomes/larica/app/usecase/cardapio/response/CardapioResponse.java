package danieldjgomes.larica.app.usecase.cardapio.response;

import danieldjgomes.larica.app.usecase.prato.response.PratoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardapioResponse {

    private String id;
    private String nome;
    private String descricao;
    private String restauranteId;
    private List<PratoResponse> pratos;
}
