package danieldjgomes.larica.core.usecases;

import danieldjgomes.larica.core.desconto.dtos.DescontoRequestDTO;
import danieldjgomes.larica.core.desconto.dtos.DescontoResponseDTO;
import danieldjgomes.larica.core.desconto.entity.Desconto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DescontoUseCase {

    DescontoResponseDTO createDesconto(DescontoRequestDTO dto);

    Optional<DescontoResponseDTO> getDescontoById(UUID id);

    List<DescontoResponseDTO> listAllDescontos();

    Optional<DescontoResponseDTO> updateDesconto(UUID id, DescontoRequestDTO dto);

    void deleteDesconto(UUID id);

    Optional<BigDecimal> aplicarDesconto(BigDecimal valorOriginal, BigDecimal porcentagemDesconto);
}
