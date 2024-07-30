package danieldjgomes.larica.app.usecase.categoria.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtualizarCategoriaRequest {


    private String nome;
    private String restauranteId;
}
