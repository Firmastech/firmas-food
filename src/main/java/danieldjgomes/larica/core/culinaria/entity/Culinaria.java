package danieldjgomes.larica.core.culinaria.entity;

import danieldjgomes.larica.core.cardapio.entity.Cardapio;
import jakarta.persistence.*;
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
@Table(name = "culinaria")
public class Culinaria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID culinariaId;

    @Column(nullable = false, length = 144)
    private String tipo;

    @OneToMany(mappedBy = "tipoCulinariaId")
    private List<Cardapio> cardapios;
}
