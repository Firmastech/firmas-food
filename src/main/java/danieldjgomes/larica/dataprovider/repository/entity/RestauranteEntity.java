package danieldjgomes.larica.dataprovider.repository.entity;

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
public class RestauranteEntity extends BaseEntity{

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "tempoEstimado", nullable = false)
    private int tempoEstimadoDeEntrega;

    @Column(name = "statusFuncionamento", nullable = false)
    private String statusFuncionamento;



    //@JoinColumn(name = "cardapioid", referencedColumnName = "id")
    //@OneToOne
    //private CardapioEntity cardapio;

    //@JoinColumn(name = "enderecoid", referencedColumnName = "id")
    //@OneToOne
    //private EnderecoEntity endereco;

}
