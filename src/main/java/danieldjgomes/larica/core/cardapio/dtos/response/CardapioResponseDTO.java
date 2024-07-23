package danieldjgomes.larica.core.cardapio.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardapioResponseDTO {

    private String id;
    private String nome;
    private String descricao;
}
