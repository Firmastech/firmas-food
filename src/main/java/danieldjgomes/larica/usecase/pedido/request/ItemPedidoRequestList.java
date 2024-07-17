package danieldjgomes.larica.usecase.pedido.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ItemPedidoRequestList {

    @NotNull(message = "O campo id não pode ser nulo")
    private UUID id;

    @NotNull(message = "O campo pratoId não pode ser nulo")
    private UUID pratoId;

    @NotBlank(message = "O campo descricao não pode estar vazio")
    private String descricao;

    @Min(value = 1, message = "A quantidade deve ser pelo menos 1")
    private int quantidade;

    @NotNull(message = "O campo precoUnitario não pode ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "O precoUnitario deve ser maior que zero")
    private BigDecimal precoUnitario;
}