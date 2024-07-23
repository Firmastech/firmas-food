package danieldjgomes.larica.core.catagoria.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaRequestDTO {

    private String nome;
    private String restauranteId;
}
