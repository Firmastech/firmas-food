package danieldjgomes.larica.adapter.database.endereco.repository;

import danieldjgomes.larica.adapter.database.endereco.model.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel,String> {
}
