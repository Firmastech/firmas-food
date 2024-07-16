package danieldjgomes.larica.core.desconto.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DescontoRequestDTO {

    private String nome;
    private String descricao;
    private BigDecimal porcentagemDesconto;
}
