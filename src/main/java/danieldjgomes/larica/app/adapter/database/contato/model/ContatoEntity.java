package danieldjgomes.larica.app.adapter.database.contato.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contato_Restaurante")
@Entity
public class ContatoEntity {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private String id;

    @Column(name = "contato")
    private String contato;

    @Column(name = "tipo_contato")
    private String tipoContato;

    @Column(name = "restaurante_id")
    private String restauranteId;

    @Column(name = "criado")
    private Date criadoEm;

    @Column(name = "atualizado")
    private Date atualizadoEm;

    @Column(name = "deletado")
    private Date deletadoEm;

    @Column(name = "ativo", nullable = false, length = 10)
    private Boolean ativo;
}
