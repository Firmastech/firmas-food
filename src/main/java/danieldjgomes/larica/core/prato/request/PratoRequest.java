package danieldjgomes.larica.core.prato.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PratoRequest {

    private String nome;
    private String descricao;
    private String urlImagem;
    private BigDecimal preco;
    private String tagDescontoId;
}
