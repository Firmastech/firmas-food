package danieldjgomes.larica.core.prato.dtos;

import danieldjgomes.larica.core.desconto.entity.Desconto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull
    @Size(max = 100)
    private String nome;

    @NotNull
    @Size(max = 2000)
    private String descricao;

    @NotNull
    @Size(max = 8000)
    private String urlImagem;

    @NotNull
    private BigDecimal preco;

    private Desconto desconto;
}
