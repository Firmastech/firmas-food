package danieldjgomes.larica.core.cardapio.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardapioUpdateRequestDTO {

    private String nome;
    private String descricao;
    private String etiqueta;
}
