package danieldjgomes.larica.app.adapter.database.contato.repository;

import danieldjgomes.larica.app.adapter.database.contato.model.ContatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoEntity,String> {
}
