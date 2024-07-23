package danieldjgomes.larica.core.catagoria.service;

import danieldjgomes.larica.core.catagoria.dtos.CategoriaRequestDTO;
import danieldjgomes.larica.core.catagoria.dtos.CategoriaResponseDTO;
import danieldjgomes.larica.core.catagoria.entity.Categoria;
import danieldjgomes.larica.core.catagoria.repository.CategoriaRepository;
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
        Categoria categoria = categoriaMapper.toEntity(categoriaRequestDTO);
        Categoria savedCategoria = categoriaRepository.save(categoria);
        return categoriaMapper.toResponseDTO(savedCategoria);
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
                .map(existingCategoria -> {
                    categoriaMapper.updateEntityFromDTO(categoriaRequestDTO, existingCategoria);
                    existingCategoria.setAtualizado(LocalDateTime.now());
                    Categoria updatedCategoria = categoriaRepository.save(existingCategoria);
                    return categoriaMapper.toResponseDTO(updatedCategoria);
                });
    }

    public void deleteCategoria(String id) {
        categoriaRepository.findById(id)
                .ifPresent(categoriaRepository::delete);
    }
}

