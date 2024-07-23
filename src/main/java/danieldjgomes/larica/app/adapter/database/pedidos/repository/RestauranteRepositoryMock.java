package danieldjgomes.larica.app.adapter.database.pedidos.repository;

import danieldjgomes.larica.dataprovider.repository.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepositoryMock extends JpaRepository<RestauranteEntity, String> {
}
