package danieldjgomes.larica.core.cardapio.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdicionarPratosRequestDTO {

    @NotNull
    private String cardapioId;

    @NotNull
    private List<String> pratoIds;
}
