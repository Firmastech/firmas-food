package danieldjgomes.larica.core.culinaria.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CulinariaResponseDTO {

    private UUID culinariaId;
    private String tipo;
}
