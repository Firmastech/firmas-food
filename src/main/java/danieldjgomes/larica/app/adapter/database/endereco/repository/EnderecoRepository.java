package danieldjgomes.larica.app.adapter.database.endereco.repository;

import danieldjgomes.larica.app.adapter.database.endereco.model.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity,String> {


    Optional<EnderecoEntity> findByCepAndNumeroAndAtivo(String cep, String numero, boolean ativo);
}
