package danieldjgomes.larica.adapter.database.contato.model;

import danieldjgomes.larica.adapter.database.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contato_Restaurante")
public class ContatoModel extends BaseModel {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "contato")
    private String contato;

    @Column(name = "tipo_contato")
    private String tipoContato;

    @Column(name = "restaurante_id")
    private String restauranteId;
}
