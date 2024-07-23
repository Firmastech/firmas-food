package danieldjgomes.larica.adapter.database.contato.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contato_Restaurante")
@Entity
public class ContatoModel {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "contato")
    private String contato;

    @Column(name = "tipo_contato")
    private String tipoContato;

    @Column(name = "restaurante_id")
    private String restauranteId;

    @Column(name = "criado")
    private LocalDateTime criadoEm;

    @Column(name = "atualizado")
    private LocalDateTime atualizadoEm;

    @Column(name = "deletado")
    private LocalDateTime deletadoEm;

    @Column(name = "ativo", nullable = false, length = 10)
    private Boolean ativo;
}
