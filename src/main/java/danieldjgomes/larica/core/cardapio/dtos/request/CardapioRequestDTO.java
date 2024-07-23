package danieldjgomes.larica.core.cardapio.dtos.request;


import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardapioRequestDTO {

    private RestauranteModel restauranteId;
    private String nome;
    private String descricao;

}
