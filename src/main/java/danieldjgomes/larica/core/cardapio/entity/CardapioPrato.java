package danieldjgomes.larica.core.cardapio.entity;


import danieldjgomes.larica.core.prato.entity.Prato;
import jakarta.persistence.*;
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
public class CardapioPrato implements Serializable{

    @Id
    @ManyToOne
    @JoinColumn(name = "cardapio_id")
    private Cardapio id;

    @ManyToOne
    @JoinColumn(name = "prato_id")
    private Prato pratoId;

}


