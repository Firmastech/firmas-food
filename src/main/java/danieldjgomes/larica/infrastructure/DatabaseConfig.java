package danieldjgomes.larica.infrastructure;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "danieldjgomes.larica.*")
@EntityScan(basePackages = "danieldjgomes.larica.*")
public class DatabaseConfig {
}
