package danieldjgomes.larica.app.adapter.database.cardapio.model;

import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
