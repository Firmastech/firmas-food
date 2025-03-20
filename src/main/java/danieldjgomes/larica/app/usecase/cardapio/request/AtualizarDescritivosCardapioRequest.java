package danieldjgomes.larica.app.usecase.cardapio.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtualizarDescritivosCardapioRequest {

    private String nome;
    private String descricao;
}
