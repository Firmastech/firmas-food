package danieldjgomes.larica.core.desconto.service;

import danieldjgomes.larica.core.desconto.entity.Desconto;
import danieldjgomes.larica.core.desconto.repository.DescontoRepository;
import danieldjgomes.larica.core.exception.EntityNotFoundException;
import danieldjgomes.larica.core.usecases.DescontoUseCase;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DescontoService implements DescontoUseCase {


    private final DescontoRepository repository;
    private final ModelMapper modelMapper;

    public Desconto createDesconto(Desconto desconto) {
        return repository.save(desconto);
    }

    public Optional<Desconto> getDescontoById(UUID id) {
        return repository.findById(id);
    }

    public List<Desconto> listAllDescontos() {
        return repository.findAll();
    }

    public Optional<Desconto> updateDesconto(UUID id, Desconto updatedDesconto) {
        Desconto existingDesconto = getExistingDesconto(id);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(updatedDesconto, existingDesconto);
        return Optional.of(repository.save(existingDesconto));
    }


    public void deleteDesconto(UUID id) {
        repository.findById(id);
    }

    public Optional<BigDecimal> aplicarDesconto(BigDecimal valorOriginal, BigDecimal porcentagemDesconto) {
        if (valorOriginal == null || porcentagemDesconto == null || porcentagemDesconto.compareTo(BigDecimal.ZERO) < 0) {
            return Optional.empty();
        }
        BigDecimal valorDescontado = valorOriginal.multiply(BigDecimal.ONE.subtract(porcentagemDesconto.divide(BigDecimal.valueOf(100))));
        return Optional.of(valorDescontado.setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    private Desconto getExistingDesconto(UUID id) {
        return getDescontoById(id).orElseThrow(() -> new EntityNotFoundException("Desconto not found with id: " + id));
    }
}
