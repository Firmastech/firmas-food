package danieldjgomes.larica.core.catagoria.service;

import danieldjgomes.larica.core.catagoria.dtos.CategoriaRequestDTO;
import danieldjgomes.larica.core.catagoria.dtos.CategoriaResponseDTO;
import danieldjgomes.larica.core.catagoria.entity.Categoria;
import danieldjgomes.larica.core.catagoria.repository.CategoriaRepository;
import danieldjgomes.larica.core.exception.EntityNotFoundException;
import danieldjgomes.larica.core.usecases.CategoriaUseCase;
import danieldjgomes.larica.infrastructure.mapper.CategoriaMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService implements CategoriaUseCase {

    private final CategoriaRepository categoriaRepository;
    private final ModelMapper modelMapper;
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
                .toList();
    }

    public Optional<CategoriaResponseDTO> updateCategoria(String id, CategoriaRequestDTO categoriaRequestDTO) {
        Categoria existingCategoria = findCategoriaById(id);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(categoriaRequestDTO, existingCategoria);
        existingCategoria.setAtualizado(LocalDateTime.now());
        Categoria updatedCategoria = categoriaRepository.save(existingCategoria);
        return Optional.of(categoriaMapper.toResponseDTO(updatedCategoria));
    }

    public void disableCategoria(String id) {
        Categoria categoria = findCategoriaById(id);
        categoria.setEstaAtivo(false);
        categoria.setDeletado(LocalDateTime.now());
        categoria.setAtualizado(LocalDateTime.now());
        categoriaRepository.save(categoria);
    }

    private Categoria findCategoriaById(String id) {
        return categoriaRepository.findById(id)
                .stream()
                .filter(Categoria::getEstaAtivo)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Categoria not found, or is disabled with id: " + id));
    }
}

