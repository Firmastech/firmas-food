package danieldjgomes.larica.app.usecase.pedido.response;

import danieldjgomes.larica.app.usecase.pedido.request.PratosRequestList;
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
    private String idPedido;
    private String statusPedido;
    private String usuarioId;
    private BigDecimal totalPedido;
    private String dataPrevisãoEntregaPedido;
    private List<PratosResponseList> itensList;
}
