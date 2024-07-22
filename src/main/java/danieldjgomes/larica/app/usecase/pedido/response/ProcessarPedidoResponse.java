package danieldjgomes.larica.app.usecase.pedido.response;

import danieldjgomes.larica.app.usecase.pedido.request.PratosRequestList;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ProcessarPedidoResponse {
    @NotNull(message = "O campo idPedido não pode ser nulo")
    private UUID idPedido;

    @NotNull(message = "O campo statusPedido não pode ser nulo")
    private String statusPedido;

    @NotNull(message = "O campo totalPedido não pode ser nulo")
    private BigDecimal totalPedido;

    @NotNull(message = "O campo dataPrevisãoEntregaPedido não pode ser nulo")
    private String dataPrevisãoEntregaPedido;

    @Valid
    @NotEmpty(message = "A lista de itens não pode estar vazia")
    private List<PratosRequestList> itensList;
}
