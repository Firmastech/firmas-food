package danieldjgomes.larica.app.usecase.cardapio.request;


import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CriarCardapioRequest {

    private RestauranteModel restauranteId;
    private String nome;
    private String descricao;

}
