package danieldjgomes.larica.core.desconto.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class DescontoResponseDTO {

    private UUID id;
    private String nome;
    private String descricao;
    private BigDecimal porcentagemDesconto;
}
