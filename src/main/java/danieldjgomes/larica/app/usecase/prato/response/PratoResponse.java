package danieldjgomes.larica.app.usecase.prato.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PratoResponse {

    private String id;
    private String descricao;
    private String nome;
    private BigDecimal preco;
    private String categoria;
    private String urlImagem;
    private BigDecimal porcentagemDesconto;

}