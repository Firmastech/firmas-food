package danieldjgomes.larica.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PapelRepository extends JpaRepository<PapelEntity,String> {

    Optional<PapelEntity> findByNomeAndAtivoTrue(String nome);
}
