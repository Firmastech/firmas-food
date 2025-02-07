package danieldjgomes.larica.app.adapter.database.contato.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contato")
@Entity
public class ContatoEntity {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private String id;

    @Column(name = "contato")
    private String contato;

    @Column(name = "tipo_contato")
    @Enumerated(EnumType.STRING)
    private TipoContato tipoContato;

    @Column(name = "criado")
    private LocalDateTime criadoEm;

    @Column(name = "atualizado")
    private LocalDateTime atualizadoEm;

    @Column(name = "deletado")
    private LocalDateTime deletadoEm;

    @Column(name = "ativo", nullable = false, length = 10)
    private Boolean ativo;
}
