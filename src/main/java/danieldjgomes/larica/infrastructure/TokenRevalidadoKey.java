package danieldjgomes.larica.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenRevalidadoKey implements Serializable {
    private String token;
    private LocalDateTime expiracao;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenRevalidadoKey that = (TokenRevalidadoKey) o;
        return Objects.equals(token, that.token) && Objects.equals(expiracao, that.expiracao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, expiracao);
    }
}
