package danieldjgomes.larica.core.prato.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PratoRequestDTO {

    private String descricao;
    private String nome;
    private BigDecimal preco;
    private String categoria;
    private String urlImagem;
    private BigDecimal porcentagemDesconto;

}
