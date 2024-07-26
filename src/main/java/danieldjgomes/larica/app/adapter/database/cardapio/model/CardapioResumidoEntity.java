package danieldjgomes.larica.app.adapter.database.cardapio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cardapio")
public class CardapioResumidoEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 8000)
    private String descricao;

    @Column(name = "restaurante_id")
    private String restauranteId;

    @Column(nullable = false)
    private Boolean estaAtivo = true;

}
