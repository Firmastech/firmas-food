package danieldjgomes.larica.app.adapter.database.pedidos.repository;

import danieldjgomes.larica.app.adapter.database.pedidos.model.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, UUID> {
}
