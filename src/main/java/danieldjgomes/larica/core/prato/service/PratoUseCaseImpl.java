package danieldjgomes.larica.core.prato.service;

import danieldjgomes.larica.core.prato.dtos.PratoRequestDTO;
import danieldjgomes.larica.core.prato.dtos.PratoResponseDTO;
import danieldjgomes.larica.core.prato.entity.PratoEntity;
import danieldjgomes.larica.core.prato.repository.PratoRepository;
import danieldjgomes.larica.core.usecases.PratoUseCase;
import danieldjgomes.larica.infrastructure.mapper.PratoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PratoUseCaseImpl implements PratoUseCase {

    private final PratoRepository pratoRepository;

    public PratoResponseDTO createPrato(PratoRequestDTO pratoRequest) {
        PratoEntity pratoEntity = PratoMapper.INSTANCE.toEntity(pratoRequest);
        PratoEntity savedPratoEntity = pratoRepository.save(pratoEntity);
        return PratoMapper.INSTANCE.toResponseDTO(savedPratoEntity);
    }

    public Optional<PratoResponseDTO> getPratoById(String id) {
        PratoEntity pratoEntity = pratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prato não encontrado"));
        return Optional.ofNullable(PratoMapper.INSTANCE.toResponseDTO(pratoEntity));
    }

    public List<PratoResponseDTO> getAllPratos() {
        return pratoRepository.findAll().stream()
                .map(PratoMapper.INSTANCE::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<PratoResponseDTO> updatePrato(String id, PratoRequestDTO pratoRequestDTO) {
        PratoEntity existingPratoEntity = pratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prato não encontrado"));

        PratoMapper.INSTANCE.updateEntityFromDTO(pratoRequestDTO, existingPratoEntity);
        existingPratoEntity.setAtualizado(LocalDateTime.now());
        PratoEntity updatedPratoEntity = pratoRepository.save(existingPratoEntity);

        return Optional.ofNullable(PratoMapper.INSTANCE.toResponseDTO(updatedPratoEntity));
    }

    public void deletePrato(String id) {
        PratoEntity pratoEntity = pratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prato não encontrado"));
        pratoEntity.setAtivo(false);
        pratoRepository.save(pratoEntity);
    }

}
