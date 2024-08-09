package danieldjgomes.larica.app.adapter.database.restaurante.model;

import danieldjgomes.larica.app.adapter.database.endereco.model.EnderecoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "restaurante")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "tempo_estimado", nullable = false)
    private int tempoEstimadoDeEntrega;

    @Column(name = "status_funcionamento", nullable = false)
    private String statusFuncionamento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private EnderecoEntity endereco;

    @Column(name = "criado")
    private Date criadoEm;

    @Column(name = "atualizado")
    private Date atualizadoEm;

    @Column(name = "deletado")
    private Date deletadoEm;

    @Column(name = "ativo", nullable = false, length = 10)
    private Boolean ativo = true;

}
