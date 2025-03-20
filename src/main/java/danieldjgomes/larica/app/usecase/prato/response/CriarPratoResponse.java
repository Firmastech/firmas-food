package danieldjgomes.larica.app.usecase.prato.response;

import lombok.*;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriarPratoResponse {

    private String id;
    private String descricao;
    private String nome;
    private BigDecimal preco;
    private String categoria;
    private String urlImagem;
    private BigDecimal porcentagemDesconto;

}
