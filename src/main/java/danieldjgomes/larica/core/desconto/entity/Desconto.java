package danieldjgomes.larica.core.desconto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class Desconto {

    @Id
    private UUID descontoId;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 2000)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal porcentagemDesconto;

}
