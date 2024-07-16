package danieldjgomes.larica.core.desconto.service;

import danieldjgomes.larica.core.desconto.dtos.DescontoRequestDTO;
import danieldjgomes.larica.core.desconto.dtos.DescontoResponseDTO;
import danieldjgomes.larica.core.desconto.entity.Desconto;
import danieldjgomes.larica.core.desconto.repository.DescontoRepository;
import danieldjgomes.larica.core.exception.EntityNotFoundException;
import danieldjgomes.larica.core.usecases.DescontoUseCase;
import danieldjgomes.larica.infrastructure.mapper.DescontoMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DescontoUseCaseImpl implements DescontoUseCase {


    private final DescontoRepository descontoRepository;

    public DescontoResponseDTO createDesconto(DescontoRequestDTO dto) {
        Desconto desconto = DescontoMapper.INSTANCE.toEntity(dto);
        desconto = descontoRepository.save(desconto);
        return DescontoMapper.INSTANCE.toDto(desconto);
    }

    public Optional<DescontoResponseDTO> getDescontoById(UUID id) {
        return descontoRepository.findById(id)
                .map(DescontoMapper.INSTANCE::toDto);
    }

    public List<DescontoResponseDTO> listAllDescontos() {
        return descontoRepository.findAll()
                .stream()
                .map(DescontoMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public Optional<DescontoResponseDTO> updateDesconto(UUID id, DescontoRequestDTO dto) {
        Desconto existingDesconto = getExistingDesconto(id);
        Desconto updatedDesconto = DescontoMapper.INSTANCE.toEntity(dto);
        updatedDesconto.setId(existingDesconto.getId());
        descontoRepository.save(updatedDesconto);
        return Optional.of(DescontoMapper.INSTANCE.toDto(updatedDesconto));
    }

    @Override
    public void deleteDesconto(UUID id) {
        Desconto existingDesconto = getExistingDesconto(id);
        descontoRepository.delete(existingDesconto);
    }

    public Optional<BigDecimal> aplicarDesconto(BigDecimal valorOriginal, BigDecimal porcentagemDesconto) {
        if (valorOriginal == null || porcentagemDesconto == null || porcentagemDesconto.compareTo(BigDecimal.ZERO) < 0) {
            return Optional.empty();
        }
        BigDecimal valorDescontado = valorOriginal.multiply(BigDecimal.ONE.subtract(porcentagemDesconto.divide(BigDecimal.valueOf(100))));
        return Optional.of(valorDescontado.setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    private Desconto getExistingDesconto(UUID id) {
        return descontoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Desconto not found with id: " + id));
    }
}
