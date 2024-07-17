package danieldjgomes.larica.usecase.pedido.request;

import danieldjgomes.larica.usecase.pedido.validation.DataHoraEntregaMaiorQueDataHoraPedido;
import danieldjgomes.larica.usecase.pedido.validation.TotalCorrespondente;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Builder
@DataHoraEntregaMaiorQueDataHoraPedido
@TotalCorrespondente
public class ProcessarPedidoRequest {

    @NotNull(message = "O campo id não pode ser nulo")
    private UUID id;

    @NotNull(message = "O campo usuarioId não pode ser nulo")
    private UUID usuarioId;

    @NotNull(message = "O campo restauranteId não pode ser nulo")
    private UUID restauranteId;

    @Valid
    @NotEmpty(message = "A lista de itens não pode estar vazia")
    private List<ItemPedidoRequestList> itensList;

    @NotNull(message = "O campo total não pode ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "O total deve ser maior que zero")
    private BigDecimal total;

    @NotBlank(message = "O campo status não pode estar vazio")
    @Pattern(regexp = "^(PREPARANDO|SAIU_PARA_ENTREGA|ENTREGUE)$",
            message = "Status Invalido!")
    private String status;

    @NotNull(message = "O campo dataHoraPedido não pode ser nulo")
    private LocalDateTime dataHoraPedido;

    @Future(message = "A Data e Hora da Entrega deve ser maior que a Data Hora Pedido")
    private LocalDateTime dataHoraEntrega;
}
