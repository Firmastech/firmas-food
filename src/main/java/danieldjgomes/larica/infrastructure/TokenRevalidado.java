package danieldjgomes.larica.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "token_revalidado")
@IdClass(TokenRevalidadoKey.class)
public class TokenRevalidado {

    @Id
    private String token;

    @Id
    private LocalDateTime expiracao;

}
