package danieldjgomes.larica.dataprovider.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "endereco")
public class EnderecoEntity {
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    @Column(name = "rua", nullable = false)
    private String rua;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "cep", length = 9, nullable = false)
    private String cep;

    @Column(name = "cidade", length = 100, nullable = false)
    private String cidade;

    @Column(name = "uf", length = 2, nullable = false)
    private String uf;

    @Column(name = "pontoreferencia", length = 144)
    private String pontoReferencia;

}
