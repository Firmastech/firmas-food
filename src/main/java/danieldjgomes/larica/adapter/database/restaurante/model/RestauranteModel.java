package danieldjgomes.larica.adapter.database.restaurante.model;

import danieldjgomes.larica.adapter.database.BaseModel;
import danieldjgomes.larica.adapter.database.endereco.model.EnderecoModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "restaurante")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteModel extends BaseModel {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "tempo_estimado", nullable = false)
    private int tempoEstimadoDeEntrega;

    @Column(name = "status_funcionamento", nullable = false)
    private String statusFuncionamento;

    @Column(name = "endereco_id")
    private String enderecoId;

    @OneToOne(cascade = CascadeType.ALL)
    private EnderecoModel endereco;

}
