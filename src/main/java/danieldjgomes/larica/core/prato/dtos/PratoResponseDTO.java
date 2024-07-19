package danieldjgomes.larica.core.prato.dtos;

import danieldjgomes.larica.core.desconto.entity.Desconto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PratoResponseDTO {

    private String id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String categoria;
    private String urlImagem;
    private Desconto desconto;

}