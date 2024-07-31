package danieldjgomes.larica.core.restaurante.entity;

import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.core.endereco.entity.Endereco;
import danieldjgomes.larica.core.restaurante.entity.enums.StatusFuncionamento;
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
    private CardapioEntity cardapio;

}
