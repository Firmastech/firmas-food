package danieldjgomes.larica.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "permissao")
@Getter
@Setter
public class PermissaoEntity {

    @Id
    @UuidGenerator
    private String id;

    private String nome;

    private String descricao;

    private LocalDateTime criado;

    private LocalDateTime atualizado;

    private LocalDateTime deletado;

    private Boolean ativo;

}
