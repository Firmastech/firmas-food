package danieldjgomes.larica.core.categoria.service;

import danieldjgomes.larica.core.categoria.dtos.CategoriaRequestDTO;
import danieldjgomes.larica.core.categoria.dtos.CategoriaResponseDTO;
import danieldjgomes.larica.core.categoria.entity.CategoriaEntity;
import danieldjgomes.larica.core.categoria.repository.CategoriaRepository;
import danieldjgomes.larica.core.usecases.CategoriaUseCase;
import danieldjgomes.larica.infrastructure.mapper.CategoriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService implements CategoriaUseCase {

    private  final CategoriaRepository categoriaRepository;

    private final CategoriaMapper categoriaMapper;

    public CategoriaResponseDTO createCategoria(CategoriaRequestDTO categoriaRequestDTO) {
        CategoriaEntity categoriaEntity = categoriaMapper.toEntity(categoriaRequestDTO);
        CategoriaEntity savedCategoriaEntity = categoriaRepository.save(categoriaEntity);
        return categoriaMapper.toResponseDTO(savedCategoriaEntity);
    }

    public Optional<CategoriaResponseDTO> getCategoriaById(String id) {
        return categoriaRepository.findById(id)
                .map(categoriaMapper::toResponseDTO);
    }

    public List<CategoriaResponseDTO> listAllCategorias() {
        return categoriaRepository.findAll().stream()
                .map(categoriaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<CategoriaResponseDTO> updateCategoria(String id, CategoriaRequestDTO categoriaRequestDTO) {
        return categoriaRepository.findById(id)
                .map(existingCategoriaEntity -> {
                    categoriaMapper.updateEntityFromDTO(categoriaRequestDTO, existingCategoriaEntity);
                    existingCategoriaEntity.setAtualizado(LocalDateTime.now());
                    CategoriaEntity updatedCategoriaEntity = categoriaRepository.save(existingCategoriaEntity);
                    return categoriaMapper.toResponseDTO(updatedCategoriaEntity);
                });
    }

    public void deleteCategoria(String id) {
        categoriaRepository.findById(id)
                .ifPresent(categoriaRepository::delete);
    }
}

