package danieldjgomes.larica.core.cardapio.dtos.response;

import danieldjgomes.larica.core.prato.dtos.PratoResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardapioResponseDTO {

    private String id;
    private String nome;
    private String descricao;
    private String restauranteId;
    private boolean estaAtivo;
    private List<PratoResponseDTO> pratos;
}
