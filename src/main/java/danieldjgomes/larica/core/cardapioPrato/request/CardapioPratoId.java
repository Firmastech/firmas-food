package danieldjgomes.larica.core.cardapioPrato.request;

import java.io.Serializable;
import java.util.Objects;

public class CardapioPratoId implements Serializable {
    private String cardapioId;
    private String pratoId;

    public CardapioPratoId() {
    }

    public CardapioPratoId(String cardapioId, String pratoId) {
        this.cardapioId = cardapioId;
        this.pratoId = pratoId;
    }

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
