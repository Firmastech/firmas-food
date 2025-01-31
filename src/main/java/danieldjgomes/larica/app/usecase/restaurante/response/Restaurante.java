package danieldjgomes.larica.app.usecase.restaurante.response;

import danieldjgomes.larica.app.usecase.endereco.response.Endereco;
import danieldjgomes.larica.app.usecase.restaurante.enums.StatusFuncionamento;
import danieldjgomes.larica.core.cardapio.entity.Cardapio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Restaurante {

    private String id;
    private String nome;
    private Integer tempoEstimadoDeEntrega;
    private StatusFuncionamento statusFuncionamento;
    private Endereco endereco;
    private Cardapio cardapio;

}
