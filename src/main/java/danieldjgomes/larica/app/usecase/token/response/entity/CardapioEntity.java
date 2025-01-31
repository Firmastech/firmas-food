package danieldjgomes.larica.app.usecase.token.response.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cardapio")
public class CardapioEntity {

    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @OneToOne
    @JoinColumn(name = "id",referencedColumnName = "id")
    private CulinariaEntity culinaria;
}
