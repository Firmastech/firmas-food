package danieldjgomes.larica.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TokenRevalidadoRepository  extends JpaRepository<TokenRevalidado, TokenRevalidadoKey> {

    Optional<TokenRevalidado> findByTokenAndExpiracaoAfter(String token, LocalDateTime currentDate);
}
