package danieldjgomes.larica.dataprovider.repository;

import danieldjgomes.larica.dataprovider.repository.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoDao extends JpaRepository<EnderecoEntity,String> {
}
