package danieldjgomes.larica.core.prato.service;

import danieldjgomes.larica.core.exception.EntityNotFoundException;
import danieldjgomes.larica.core.prato.dtos.PratoRequestDTO;
import danieldjgomes.larica.core.prato.dtos.PratoResponseDTO;
import danieldjgomes.larica.core.prato.entity.Prato;
import danieldjgomes.larica.core.prato.repository.PratoRepository;
import danieldjgomes.larica.core.usecases.PratoUseCase;
import danieldjgomes.larica.infrastructure.mapper.PratoMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PratoUseCaseImpl implements PratoUseCase {

    private final PratoRepository pratoRepository;
    private final ModelMapper modelMapper;

    public PratoResponseDTO createPrato(PratoRequestDTO pratoRequest) {
        Prato prato = PratoMapper.INSTANCE.toEntity(pratoRequest);
        Prato savedPrato = pratoRepository.save(prato);
        return PratoMapper.INSTANCE.toResponseDTO(savedPrato);
    }

    public Optional<PratoResponseDTO> findPratoById(String id) {
        Prato prato = getPratoById(id);
        return Optional.ofNullable(PratoMapper.INSTANCE.toResponseDTO(prato));
    }

    public List<PratoResponseDTO> getAllPratos() {
        return pratoRepository.findAll()
                .stream()
                .filter(Prato::getEstaAtivo)
                .map(PratoMapper.INSTANCE::toResponseDTO)
                .toList();
    }

    public Optional<PratoResponseDTO> updatePrato(String id, PratoRequestDTO pratoRequest) {
        Prato existingPrato = getPratoById(id);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(pratoRequest, existingPrato);
        existingPrato.setAtualizado(LocalDateTime.now());
        Prato updatedPrato = pratoRepository.save(existingPrato);
        return Optional.ofNullable(PratoMapper.INSTANCE.toResponseDTO(updatedPrato));
    }

    public void disablePrato(String id) {
        Prato prato = getPratoById(id);
        prato.setAtualizado(LocalDateTime.now());
        prato.setEstaAtivo(false);
        prato.setDeletado(LocalDateTime.now());
        pratoRepository.save(prato);
    }

    private Prato getPratoById(String id) {
        return pratoRepository.findById(id)
                .stream()
                .filter(Prato::getEstaAtivo)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Prato not found, or is disabled with id: " + id));
    }

}
