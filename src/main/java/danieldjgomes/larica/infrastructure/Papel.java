package danieldjgomes.larica.infrastructure;

import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "papel")
@Data
public class Papel {

    @Id
    @UuidGenerator
    @Column(name = "id", nullable = false)
    private String id;

    private String nome;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "papel_permissao",
            joinColumns = @JoinColumn(name = "papel_id"),
            inverseJoinColumns = @JoinColumn(name = "permissao_id")
    )
    private Set<Permissao> permissoes = new HashSet<>();

    @OneToOne
    private RestauranteEntity restaurante;

    private Date criado;

    private Date atualizado;

    private Date deletado;

    private Boolean ativo;
}
