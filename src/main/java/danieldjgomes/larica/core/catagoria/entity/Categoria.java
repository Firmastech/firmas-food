package danieldjgomes.larica.core.catagoria.entity;

import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categoria_prato")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private RestauranteModel restaurante;

    @Column(nullable = false)
    private LocalDateTime criado = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime atualizado = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean estaAtivo = true;

    private LocalDateTime deletado;
}
