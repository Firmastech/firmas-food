package danieldjgomes.larica.core.desconto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tag_desconto")
public class Desconto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID descontoId;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "descicao", nullable = false, length = 2000)
    private String descricao;

    @Column(name = "porcentagemdesconto", nullable = false)
    private BigDecimal porcentagemDesconto;

}
