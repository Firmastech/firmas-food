package danieldjgomes.larica.app.adapter.database.pedidos.repository;

import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {
}
