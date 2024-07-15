package danieldjgomes.larica.core.cardapio.entity;

import danieldjgomes.larica.core.culinaria.entity.Culinaria;
import danieldjgomes.larica.core.prato.entity.Prato;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cardapio")
public class Cardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID cardapioId;

    @ManyToOne
    @JoinColumn(name = "pratoId", nullable = false)
    private Prato pratoId;

    @ManyToOne
    @JoinColumn(name = "tipoCulinariaId", nullable = false)
    private Culinaria tipoCulinariaId;
}
