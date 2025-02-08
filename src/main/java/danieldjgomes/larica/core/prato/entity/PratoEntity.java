package danieldjgomes.larica.core.prato.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prato")
public class PratoEntity {

    @Id
    @UuidGenerator
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "preco", nullable = false)
    private BigDecimal preco;

    @Column(name = "url_imagem")
    private String urlImagem;

    @Column(name = "porcentagem_desconto")
    private BigDecimal porcentagemDesconto;

    @Column(name = "criado", nullable = false)
    private LocalDateTime criado = LocalDateTime.now();

    @Column(name = "atualizado", nullable = false)
    private LocalDateTime atualizado = LocalDateTime.now();

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    @Column(name = "deletado")
    private LocalDateTime deletado;

}
