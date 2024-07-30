package danieldjgomes.larica.app.adapter.database.categoria.impl;

import danieldjgomes.larica.app.adapter.database.categoria.model.CategoriaEntity;
import danieldjgomes.larica.app.adapter.database.categoria.repository.CategoriaRepository;
import danieldjgomes.larica.app.ports.database.CategoriaPersist;
import danieldjgomes.larica.app.usecase.categoria.exception.CategoriaNotFoundException;
import danieldjgomes.larica.app.usecase.categoria.request.AtualizarCategoriaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaPersistImpl implements CategoriaPersist {

    private final CategoriaRepository categoriaRepository;

    @Override
    public CategoriaEntity createCategoria(CategoriaEntity categoria) {
        Date dateAtual = new Date();
        categoria.setCriado(dateAtual);
        categoria.setAtualizado(dateAtual);
        return categoriaRepository.save(categoria);
    }

    public Optional<CategoriaEntity> getCategoriaById(String id) {
        return categoriaRepository.findCategoriaAtivoById(id);
    }

    public List<CategoriaEntity> listAllCategorias() {
        return categoriaRepository.findAllAtivos();
    }

    public Optional<CategoriaEntity> updateCategoria(String id, AtualizarCategoriaRequest categoriaRequest) {
        Integer updatedRown = categoriaRepository.atualizarCategoria(
                id,
                categoriaRequest.getNome(),
                categoriaRequest.getRestauranteId(),
                new Date()
        );
        if (updatedRown > 0) {
            return Optional.ofNullable(categoriaRepository.findCategoriaAtivoById(id).orElseThrow(CategoriaNotFoundException::new));
        }
        throw new CategoriaNotFoundException();
    }

    public void disableCategoria(String id) {
        categoriaRepository.desativarCategoria(id, new Date());
    }

}

