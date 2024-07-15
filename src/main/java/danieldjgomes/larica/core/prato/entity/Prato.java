package danieldjgomes.larica.core.prato.entity;

import danieldjgomes.larica.core.desconto.entity.Desconto;
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
public class Prato {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID pratoId;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 2000)
    private String descricao;

    @Column(nullable = false, length = 8000)
    private String urlImagem;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "tag_desconto_id", nullable = false)
    private Desconto tagDescontoId;

}
