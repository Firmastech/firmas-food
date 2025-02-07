package danieldjgomes.larica.core.CardapioPrato.entity;


import danieldjgomes.larica.core.cardapio.entity.CardapioEntity;
import danieldjgomes.larica.core.prato.entity.PratoEntity;
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
    private CardapioEntity cardapioEntity;

    @ManyToOne
    @JoinColumn(name = "prato_id", nullable = false)
    private PratoEntity pratoEntity;

    public CardapioPrato(CardapioEntity cardapioEntity, PratoEntity pratoEntity) {
        this.cardapioEntity = cardapioEntity;
        this.pratoEntity = pratoEntity;
    }

}


