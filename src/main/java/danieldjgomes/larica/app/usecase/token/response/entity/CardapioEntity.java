package danieldjgomes.larica.app.usecase.token.response.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "cardapio")
public class CardapioEntity {

    @Id
    @UuidGenerator
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @OneToOne
    @JoinColumn(name = "id",referencedColumnName = "id")
    private CulinariaEntity culinaria;
}
