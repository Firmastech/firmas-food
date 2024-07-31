package danieldjgomes.larica.app.usecase.prato.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtualizarPratoResponse {

    private String descricao;
    private String nome;
    private BigDecimal preco;
    private String urlImagem;
    private BigDecimal porcentagemDesconto;
}
