package danieldjgomes.larica.core.prato.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PratoResponseDTO {

    private String id;
    private String descricao;
    private String nome;
    private BigDecimal preco;
    private String categoria;
    private String urlImagem;
    private BigDecimal porcentagemDesconto;
    private LocalDateTime criado;
    private LocalDateTime atualizado;
    private Boolean estaAtivo;
    private LocalDateTime deletado;

}