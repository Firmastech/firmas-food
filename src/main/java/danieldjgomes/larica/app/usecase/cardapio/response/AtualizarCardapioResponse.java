package danieldjgomes.larica.app.usecase.cardapio.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtualizarCardapioResponse {

    private String id;
    private String nome;
    private String descricao;
}
