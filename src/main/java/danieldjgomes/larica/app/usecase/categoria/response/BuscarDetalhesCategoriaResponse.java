package danieldjgomes.larica.app.usecase.categoria.response;

import danieldjgomes.larica.app.usecase.prato.response.PratoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuscarDetalhesCategoriaResponse {

        private String id;
        private String nome;
        private List<PratoResponse> pratos;
}
