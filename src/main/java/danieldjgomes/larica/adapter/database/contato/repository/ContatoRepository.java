package danieldjgomes.larica.adapter.database.contato.repository;

import danieldjgomes.larica.adapter.database.contato.model.ContatoModel;
import danieldjgomes.larica.adapter.database.endereco.model.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoModel,String> {
}
