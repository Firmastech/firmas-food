package danieldjgomes.larica.adapter.database.endereco.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "endereco")
@Getter
@Setter
public class EnderecoModel {
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    @Column(name = "rua", nullable = false)
    private String rua;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "cep", length = 9, nullable = false)
    private String cep;

    @Column(name = "cidade", length = 100, nullable = false)
    private String cidade;

    @Column(name = "uf", length = 2, nullable = false)
    private String uf;

    @Column(name = "ponto_referencia", length = 144)
    private String pontoReferencia;

    @Column(name = "criado")
    private Date criadoEm;

    @Column(name = "atualizado")
    private Date atualizadoEm;

    @Column(name = "deletado")
    private Date deletadoEm;

    @Column(name = "ativo", nullable = false, length = 10)
    private Boolean ativo;

    @PrePersist
    protected void onCreate() {
        criadoEm = new Date();
        ativo = true;
    }

}
