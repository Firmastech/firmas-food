package danieldjgomes.larica.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;

@Entity
@Table(name = "permissao")
@Data
public class Permissao {

    @Id
    @UuidGenerator
    private String id;

    private String nome;

    private String descricao;

    private Date criado;

    private Date atualizado;

    private Date deletado;

    private Boolean ativo;

}
