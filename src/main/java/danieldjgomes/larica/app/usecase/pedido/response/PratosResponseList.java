package danieldjgomes.larica.app.usecase.pedido.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PratosResponseList {
    private String pratoId;
    private String descricao;
    private String restauranteId;
    private int quantidade;
}