package danieldjgomes.larica.app.usecase.cardapioPrato.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class CardapioPratoId implements Serializable {
    private String cardapioId;
    private String pratoId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardapioPratoId that = (CardapioPratoId) o;
        return Objects.equals(cardapioId, that.cardapioId) &&
                Objects.equals(pratoId, that.pratoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardapioId, pratoId);
    }
}
