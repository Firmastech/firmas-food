package danieldjgomes.larica.dataprovider.repository.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurante")
public class RestauranteEntity {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @JoinColumn(name = "enderecoid", referencedColumnName = "id")
    @OneToOne
    private EnderecoEntity endereco;

    @Column(name = "tempoEstimado", nullable = false)
    private int tempoEstimado;

    @Column(name = "statusFuncionamento", nullable = false, length = 10)
    private String statusFuncionamento;

    @JoinColumn(name = "cardapioid", referencedColumnName = "id")
    @OneToOne
    private CardapioEntity cardapio;

}
