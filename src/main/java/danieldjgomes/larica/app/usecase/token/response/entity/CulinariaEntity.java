package danieldjgomes.larica.app.usecase.token.response.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "culinaria")
public class CulinariaEntity {

    @Id
    @UuidGenerator
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "tipo", nullable = false, length = 144)
    private String tipo;
}
