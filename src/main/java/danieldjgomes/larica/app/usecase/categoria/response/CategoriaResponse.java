package danieldjgomes.larica.app.usecase.categoria.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponse {

    private String id;
    private String nome;
    private String restauranteId;

}
