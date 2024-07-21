package danieldjgomes.larica.core.culinaria.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CulinariaRequestDTO {

    @NotNull
    @Size(max = 144)
    private String tipo;

}

