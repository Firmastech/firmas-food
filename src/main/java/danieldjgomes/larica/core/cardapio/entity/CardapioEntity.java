package danieldjgomes.larica.core.cardapio.entity;

import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;
import danieldjgomes.larica.core.categoria.entity.CategoriaEntity;
import danieldjgomes.larica.core.prato.entity.PratoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cardapio")
public class CardapioEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 8000)
    private String descricao;

    private String etiqueta;

    @OneToMany(mappedBy = "id")
    private Set<CategoriaEntity> categotias;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private RestauranteEntity restaurante;

    @Column(nullable = false)
    private LocalDateTime criado = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime atualizado = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean estaAtivo = true;

    private LocalDateTime deletado;

}
