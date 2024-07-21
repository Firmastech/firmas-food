package danieldjgomes.larica.core.prato.dtos;

import danieldjgomes.larica.core.desconto.entity.Desconto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PratoResponseDTO {
    private UUID id;
    private String nome;
    private String descricao;
    private String urlImagem;
    private BigDecimal preco;
    private Desconto desconto;
}