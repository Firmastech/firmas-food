package danieldjgomes.larica.app.usecase.pedido.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PratosRequestList {

    @NotNull(message = "O campo pratoId não pode ser nulo")
    private String pratoId;

    private String descricao;

    @NotBlank(message = "O campo restauranteId não pode estar vazio")
    private String restauranteId;

    @Min(value = 1, message = "A quantidade deve ser pelo menos 1")
    private int quantidade;
}