package danieldjgomes.larica.adapter.database.endereco.repository;

import danieldjgomes.larica.adapter.database.endereco.model.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel,String> {

    Optional<EnderecoModel> findByCepAndNumero(String cep, String numero);
}
