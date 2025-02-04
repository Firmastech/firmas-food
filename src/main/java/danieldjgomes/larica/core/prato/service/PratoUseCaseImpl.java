package danieldjgomes.larica.core.prato.service;

import danieldjgomes.larica.core.prato.dtos.PratoRequestDTO;
import danieldjgomes.larica.core.prato.dtos.PratoResponseDTO;
import danieldjgomes.larica.core.prato.entity.Prato;
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
        Prato prato = PratoMapper.INSTANCE.toEntity(pratoRequest);
        Prato savedPrato = pratoRepository.save(prato);
        return PratoMapper.INSTANCE.toResponseDTO(savedPrato);
    }

    public Optional<PratoResponseDTO> getPratoById(String id) {
        Prato prato = pratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prato não encontrado"));
        return Optional.ofNullable(PratoMapper.INSTANCE.toResponseDTO(prato));
    }

    public List<PratoResponseDTO> getAllPratos() {
        return pratoRepository.findAll().stream()
                .map(PratoMapper.INSTANCE::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<PratoResponseDTO> updatePrato(String id, PratoRequestDTO pratoRequestDTO) {
        Prato existingPrato = pratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prato não encontrado"));

        PratoMapper.INSTANCE.updateEntityFromDTO(pratoRequestDTO, existingPrato);
        existingPrato.setAtualizado(LocalDateTime.now());
        Prato updatedPrato = pratoRepository.save(existingPrato);

        return Optional.ofNullable(PratoMapper.INSTANCE.toResponseDTO(updatedPrato));
    }

    public void deletePrato(String id) {
        Prato prato = pratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prato não encontrado"));
        prato.setEstaAtivo(false);
        pratoRepository.save(prato);
    }

}
