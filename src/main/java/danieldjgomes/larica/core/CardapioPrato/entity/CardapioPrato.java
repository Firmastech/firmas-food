package danieldjgomes.larica.core.CardapioPrato.entity;


import danieldjgomes.larica.core.cardapio.entity.Cardapio;
import danieldjgomes.larica.core.prato.entity.Prato;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cardapio_prato")
public class CardapioPrato{

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "cardapio_id", nullable = false)
    private Cardapio cardapio;

    @ManyToOne
    @JoinColumn(name = "prato_id", nullable = false)
    private Prato prato;

    public CardapioPrato(Cardapio cardapio, Prato prato) {
        this.cardapio = cardapio;
        this.prato = prato;
    }

}


