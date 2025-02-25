package danieldjgomes.larica.app.adapter.database.categoria.impl;

import danieldjgomes.larica.app.adapter.database.categoria.repository.CategoriaRepository;
import danieldjgomes.larica.app.ports.database.CategoriaPersist;
import danieldjgomes.larica.core.categoria.entity.CategoriaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaPersistImpl implements CategoriaPersist {

    private final CategoriaRepository categoriaRepository;

    @Override
    public CategoriaEntity criarCategoria(CategoriaEntity categoria) {
        LocalDateTime dateAtual = LocalDateTime.now();
        categoria.setCriado(dateAtual);
        categoria.setAtualizado(dateAtual);
        return categoriaRepository.save(categoria);
    }

    public Optional<CategoriaEntity> getCategoriaById(String id) {
        return categoriaRepository.findCategoriaAtivoById(id);
    }

    public List<CategoriaEntity> buscarCategorias(String cardapioId) {
        return categoriaRepository.findAllAtivos();
    }

    public Optional<CategoriaEntity> updateCategoria(CategoriaEntity categoria) {
        return Optional.of(categoriaRepository.save(categoria));
    }

    public void disableCategoria(String id) {
        categoriaRepository.desativarCategoria(id, new Date());
    }

}

