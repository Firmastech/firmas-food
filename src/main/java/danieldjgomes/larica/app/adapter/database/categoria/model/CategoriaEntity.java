package danieldjgomes.larica.app.adapter.database.categoria.model;


import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.adapter.database.prato.model.PratoEntity;
import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name = "categoria_prato",
            joinColumns = @JoinColumn(name = "categoria_id"),
            inverseJoinColumns = @JoinColumn(name = "prato_id")
    )
    private Set<PratoEntity> pratos;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private RestauranteEntity restaurante;

    @ManyToMany
    @JoinTable(
            name = "cardapio_categoria",
            joinColumns = @JoinColumn(name = "cardapio_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<CardapioEntity> cardapios;

    @Column(nullable = false)
    private LocalDateTime criado;

    @Column(nullable = false)
    private LocalDateTime atualizado;

    @Column(nullable = false)
    private Boolean ativo = true;

    private LocalDateTime deletado;
}
