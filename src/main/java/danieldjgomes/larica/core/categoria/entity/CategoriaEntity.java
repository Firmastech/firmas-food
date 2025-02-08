package danieldjgomes.larica.core.categoria.entity;

import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;
import danieldjgomes.larica.core.prato.entity.PratoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categoria_prato")
public class CategoriaEntity {

    @Id
    @UuidGenerator
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String etiqueta;

    @OneToMany(mappedBy = "id")
    private Set<PratoEntity> pratos;

    @Column(nullable = false)
    private LocalDateTime criado = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime atualizado = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean ativo = true;

    private LocalDateTime deletado;
}
