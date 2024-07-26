package danieldjgomes.larica.core.cardapioPrato.entity;


import danieldjgomes.larica.core.cardapioPrato.request.CardapioPratoId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cardapio_prato")
@IdClass(CardapioPratoId.class)
public class CardapioPrato implements Serializable {

    @Id
    private String cardapioId;

    @Id
    private String pratoId;

}

