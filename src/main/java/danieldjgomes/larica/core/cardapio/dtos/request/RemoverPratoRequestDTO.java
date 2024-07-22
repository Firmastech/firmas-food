package danieldjgomes.larica.core.cardapio.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RemoverPratoRequestDTO {

    @NotNull
    private String cardapioId;

    @NotNull
    private String pratoId;
}
