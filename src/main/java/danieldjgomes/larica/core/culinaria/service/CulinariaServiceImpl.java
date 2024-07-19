package danieldjgomes.larica.core.culinaria.service;

import danieldjgomes.larica.core.culinaria.dtos.CulinariaRequestDTO;
import danieldjgomes.larica.core.culinaria.dtos.CulinariaResponseDTO;
import danieldjgomes.larica.core.culinaria.entity.Culinaria;
import danieldjgomes.larica.core.culinaria.repository.CulinariaRepository;
import danieldjgomes.larica.core.exception.EntityNotFoundException;
import danieldjgomes.larica.core.usecases.CulinariaUseCase;
import danieldjgomes.larica.infrastructure.mapper.CulinariaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CulinariaServiceImpl implements CulinariaUseCase {

    private final CulinariaRepository culinariaRepository;
    private final CulinariaMapper culinariaMapper;

    public CulinariaResponseDTO createCulinaria(CulinariaRequestDTO culinariaRequest) {
        Culinaria culinaria = culinariaMapper.toEntity(culinariaRequest);
        culinaria = culinariaRepository.save(culinaria);
        return culinariaMapper.toDto(culinaria);
    }

    public Optional<CulinariaResponseDTO> getCulinariaById(String id) {
        return culinariaRepository.findById(id)
                .map(culinariaMapper::toDto);
    }

    public List<CulinariaResponseDTO> listAllCulinarias() {
        List<Culinaria> culinarias = culinariaRepository.findAll();
        return culinariaMapper.toDtoList(culinarias);
    }

    public Optional<CulinariaResponseDTO> updateCulinaria(String id, CulinariaRequestDTO culinariaRequest) {
        Culinaria existingCulinaria = getExistingCulinaria(id);
        Culinaria updatedCulinaria = culinariaMapper.toEntity(culinariaRequest);
        updatedCulinaria.setCulinariaId(existingCulinaria.getCulinariaId());
        culinariaRepository.save(updatedCulinaria);
        return Optional.of(culinariaMapper.toDto(updatedCulinaria));
    }

    public void deleteCulinaria(String id) {
        Culinaria existingCulinaria = getExistingCulinaria(id);
        culinariaRepository.delete(existingCulinaria);
    }

    private Culinaria getExistingCulinaria(String id) {
        return culinariaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Culinaria not found with id: " + id));
    }

}
