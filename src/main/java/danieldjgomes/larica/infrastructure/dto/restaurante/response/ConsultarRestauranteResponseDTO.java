package danieldjgomes.larica.infrastructure.dto.restaurante.response;

import danieldjgomes.larica.core.restaurante.entity.enums.StatusFuncionamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultarRestauranteResponseDTO {

    private String id;
    private String nome;
    private Integer tempoEstimadoDeEntrega;
    private StatusFuncionamento statusFuncionamento;
    //private Endereco endereco;
    //private Cardapio cardapio;
}
