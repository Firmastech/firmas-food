package danieldjgomes.larica.core.usecases;

import danieldjgomes.larica.core.desconto.entity.Desconto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DescontoUseCase {

    Desconto createDesconto(Desconto desconto);

    Optional<Desconto> getDescontoById(UUID id);

    List<Desconto> listAllDescontos();

    Optional<Desconto> updateDesconto(UUID id, Desconto updatedDesconto);

    void deleteDesconto(UUID id);

    Optional<BigDecimal> aplicarDesconto(BigDecimal valorOriginal, BigDecimal porcentagemDesconto);
}
