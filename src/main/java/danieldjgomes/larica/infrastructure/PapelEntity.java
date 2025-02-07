package danieldjgomes.larica.infrastructure;

import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "papel")
@Getter
@Setter
public class PapelEntity {

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
    private Set<PermissaoEntity> permissoes = new HashSet<>();

    private LocalDateTime criado;

    private LocalDateTime atualizado;

    private LocalDateTime deletado;

    private Boolean ativo;
}
