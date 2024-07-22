package danieldjgomes.larica.core.contato.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contato {
    private String id;
    private String contato;
    private String tipoContato;
    private String restauranteId;
}
