package danieldjgomes.larica.core.cardapio.entity;

import danieldjgomes.larica.core.culinaria.entity.Culinaria;
import danieldjgomes.larica.core.prato.entity.Prato;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Cardapio {

    @Id
    private UUID cardapioId;

    @ManyToOne
    @JoinColumn(name = "pratoId", nullable = false)
    private Prato prato;

    @ManyToOne
    @JoinColumn(name = "tipoCulinariaId", nullable = false)
    private Culinaria culinaria;
}
