package danieldjgomes.larica.app.adapter.database.pedidos.repository;

import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepositoryMock extends JpaRepository<RestauranteEntity, String> {
}
