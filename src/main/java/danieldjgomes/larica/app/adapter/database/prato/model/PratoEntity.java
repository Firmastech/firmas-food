package danieldjgomes.larica.app.adapter.database.prato.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prato")
public class PratoEntity {

    @Id
    private String id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "preco", nullable = false)
    private BigDecimal preco;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "url_imagem")
    private String urlImagem;

    @Column(name = "porcentagem_desconto")
    private BigDecimal porcentagemDesconto;

    @Column(name = "criado", nullable = false)
    private Date criado;

    @Column(name = "atualizado", nullable = false)
    private Date atualizado;

    @Column(name = "esta_ativo", nullable = false)
    private Boolean estaAtivo = true;

    @Column(name = "deletado")
    private Date deletado;

}
