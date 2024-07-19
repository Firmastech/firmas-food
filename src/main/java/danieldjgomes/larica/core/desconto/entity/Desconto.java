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
@Table(name = "desconto")
public class Desconto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "desconto_id")
    private String id;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "descricao", length = 2000)
    private String descricao;

    @Column(name = "porcentagem_desconto")
    private BigDecimal porcentagemDesconto;

}
