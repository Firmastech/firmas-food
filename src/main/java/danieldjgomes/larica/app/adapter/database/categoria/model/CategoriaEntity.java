package danieldjgomes.larica.app.adapter.database.categoria.model;

import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categoria_prato")
public class CategoriaEntity {

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
    private Date criado;

    @Column(nullable = false)
    private Date atualizado;

    @Column(nullable = false)
    private Boolean estaAtivo = true;

    private Date deletado;
}
