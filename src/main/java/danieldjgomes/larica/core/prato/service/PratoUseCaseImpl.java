package danieldjgomes.larica.core.prato.service;

import danieldjgomes.larica.core.desconto.entity.Desconto;
import danieldjgomes.larica.core.desconto.repository.DescontoRepository;
import danieldjgomes.larica.core.exception.EntityNotFoundException;
import danieldjgomes.larica.core.prato.dtos.PratoRequestDTO;
import danieldjgomes.larica.core.prato.dtos.PratoResponseDTO;
import danieldjgomes.larica.core.prato.entity.Prato;
import danieldjgomes.larica.core.prato.repository.PratoRepository;
import danieldjgomes.larica.core.usecases.PratoUseCase;
import danieldjgomes.larica.infrastructure.mapper.PratoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PratoUseCaseImpl implements PratoUseCase {

    private final PratoRepository pratoRepository;
    private final DescontoRepository descontoRepository;


    public PratoResponseDTO createPrato(PratoRequestDTO pratoRequest) {
        Prato prato = PratoMapper.INSTANCE.toEntity(pratoRequest);
        Prato savedPrato = pratoRepository.save(prato);
        return PratoMapper.INSTANCE.toDto(savedPrato);
    }


    public PratoResponseDTO applayDescontoToPrato(UUID pratoId, UUID descontoId) {
        Prato prato = getExistingPrato(pratoId);
        Desconto desconto = findDescontoById(descontoId);
        prato.setDesconto(desconto);
        BigDecimal precoOriginal = prato.getPreco();
        BigDecimal porcentagemDesconto = desconto.getPorcentagemDesconto();
        BigDecimal valorDesconto = precoOriginal.multiply(porcentagemDesconto).divide(BigDecimal.valueOf(100));
        BigDecimal precoFinal = precoOriginal.subtract(valorDesconto);
        prato.setPreco(precoFinal);

        Prato savedPrato = pratoRepository.save(prato);
        return PratoMapper.INSTANCE.toDto(savedPrato);
    }

    public Optional<PratoResponseDTO> getPratoById(UUID id) {
        return pratoRepository.findById(id)
                .map(PratoMapper.INSTANCE::toDto);
    }

    public List<PratoResponseDTO> listAllPratos() {
        return pratoRepository.findAll()
                .stream()
                .map(PratoMapper.INSTANCE::toDto)
                .toList();
    }

    public Optional<PratoResponseDTO> updatePrato(UUID id, PratoRequestDTO pratoRequest) {
        Prato existingPrato = getExistingPrato(id);
        Prato updatedPrato = PratoMapper.INSTANCE.toEntity(pratoRequest);
        updatedPrato.setId(existingPrato.getId());
        Desconto desconto = findDescontoById(pratoRequest.getDesconto().getId());
        updatedPrato.setDesconto(desconto);
        pratoRepository.save(updatedPrato);
        return Optional.of(PratoMapper.INSTANCE.toDto(updatedPrato));
    }

    public void deletePrato(UUID id) {
        Prato existingPrato = getExistingPrato(id);
        pratoRepository.delete(existingPrato);
    }

    private Prato getExistingPrato(UUID id) {
        return pratoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prato not found with id: " + id));
    }

    private Desconto findDescontoById(UUID descontoId) {
        return descontoRepository.findById(descontoId)
                .orElseThrow(() -> new EntityNotFoundException("Desconto not found with id: " + descontoId));
    }
}
