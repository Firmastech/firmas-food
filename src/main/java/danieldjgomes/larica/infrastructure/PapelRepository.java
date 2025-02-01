package danieldjgomes.larica.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PapelRepository extends JpaRepository<Papel,String> {

    Optional<Papel> findByRestauranteIdAndNomeAndAtivoTrue(String id, String nome);
}
