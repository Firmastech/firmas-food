package danieldjgomes.larica.app.usecase;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GerarUUIDUseCaseImpl implements GerarUUIDUseCase {

    public String gerar() {
        return UUID.randomUUID().toString();
    }
}
