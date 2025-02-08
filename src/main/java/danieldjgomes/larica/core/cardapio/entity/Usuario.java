package danieldjgomes.larica.core.cardapio.entity;

import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @UuidGenerator
    @Column(name = "id", nullable = false)
    private String id;

    @OneToOne
    private RestauranteEntity restaurante;

    private String email;
    private String senha;
    private Boolean ativo;
    private String primeiroNome;
    private String segundoNome;

}
