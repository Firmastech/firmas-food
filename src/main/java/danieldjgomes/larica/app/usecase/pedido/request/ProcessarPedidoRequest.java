package danieldjgomes.larica.app.usecase.pedido.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessarPedidoRequest {

    private String id;

    @NotNull(message = "O campo usuarioId não pode ser nulo")
    private String usuarioId;

    @Valid
    @NotEmpty(message = "A lista de itens não pode estar vazia")
    @Builder.Default
    private List<PratosRequestList> itensList = new ArrayList<>();

}
