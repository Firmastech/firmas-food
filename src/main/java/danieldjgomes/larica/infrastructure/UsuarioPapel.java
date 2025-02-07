package danieldjgomes.larica.infrastructure;

import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario_papel")
public class UsuarioPapel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;
}
