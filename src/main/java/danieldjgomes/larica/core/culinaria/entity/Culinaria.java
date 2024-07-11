package danieldjgomes.larica.core.culinaria.entity;

import danieldjgomes.larica.core.cardapio.entity.Cardapio;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Culinaria {
    @Id
    private UUID culinariaId;

    @Column(nullable = false, length = 144)
    private String tipo;

    @OneToMany(mappedBy = "culinaria")
    private List<Cardapio> cardapios;
}
