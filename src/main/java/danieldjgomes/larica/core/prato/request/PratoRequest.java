package danieldjgomes.larica.core.prato.request;

import danieldjgomes.larica.core.desconto.entity.Desconto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class PratoRequest {

    private String nome;
    private String descricao;
    private String urlImagem;
    private BigDecimal preco;
    private UUID tagDescontoId;
}
