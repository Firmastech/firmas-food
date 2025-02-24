package danieldjgomes.larica.core.categoria.entity;


import danieldjgomes.larica.app.adapter.database.prato.model.PratoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categoria")
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
    private LocalDateTime criado;

    @Column(nullable = false)
    private LocalDateTime atualizado;

    @Column(nullable = false)
    private Boolean ativo = true;

    private LocalDateTime deletado;
}
