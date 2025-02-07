package danieldjgomes.larica.app.adapter.database.restaurante.model;

import danieldjgomes.larica.app.adapter.database.contato.model.ContatoEntity;
import danieldjgomes.larica.app.adapter.database.endereco.model.EnderecoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "restaurante")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteEntity {

    @Id
    @UuidGenerator
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

    @OneToMany(mappedBy = "id")
    private Set<ContatoEntity> contatos;

    @Column(name = "criado")
    private LocalDateTime criadoEm;

    @Column(name = "atualizado")
    private LocalDateTime atualizadoEm;

    @Column(name = "deletado")
    private LocalDateTime deletadoEm;

    @Column(name = "ativo", nullable = false, length = 10)
    private Boolean ativo = true;

}
