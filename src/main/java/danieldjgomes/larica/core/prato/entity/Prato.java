package danieldjgomes.larica.core.prato.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prato")
public class Prato {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;

    private String descricao;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal preco;

    private String categoria;

    @Column(name = "url_imagem")
    private String urlImagem;

    @Column(name = "porcentagem_desconto")
    private BigDecimal porcentagemDesconto;

    @Column(nullable = false)
    private LocalDateTime criado = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime atualizado = LocalDateTime.now();

    @Column(name = "esta_ativo", nullable = false)
    private Boolean estaAtivo = true;

    private LocalDateTime deletado;


}
