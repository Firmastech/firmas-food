package danieldjgomes.larica.dataprovider.repository;


import danieldjgomes.larica.dataprovider.repository.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteDao extends JpaRepository<RestauranteEntity, String> {
}
