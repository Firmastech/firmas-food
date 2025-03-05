package danieldjgomes.larica.app.adapter.database.categoria.impl;

import danieldjgomes.larica.app.adapter.database.categoria.repository.CategoriaRepository;
import danieldjgomes.larica.app.ports.database.CategoriaPersist;
import danieldjgomes.larica.app.adapter.database.categoria.model.CategoriaEntity;
import danieldjgomes.larica.app.usecase.GerarUUIDUseCase;
import danieldjgomes.larica.app.usecase.cardapio.exception.CardapioNotFoundException;
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
    private final GerarUUIDUseCase gerarUUIDUseCase;


    @Override
    public CategoriaEntity criarCategoria(CategoriaEntity categoria) {
        LocalDateTime dateAtual = LocalDateTime.now();
        categoria.setId(gerarUUIDUseCase.gerar());
        categoria.setCriado(dateAtual);
        return categoriaRepository.save(categoria);
    }

    public Optional<CategoriaEntity> buscarDetalhesCategoria(String id) {
        return categoriaRepository.findCategoriaAtivoById(id);
    }

    public List<CategoriaEntity> buscarCategorias(String cardapioId) {
        return categoriaRepository.findAllAtivos();
    }

    public Optional<CategoriaEntity> updateCategoria(CategoriaEntity categoria) {
        categoria.setAtualizado(LocalDateTime.now());
        return Optional.of(categoriaRepository.save(categoria));
    }

    public void disableCategoria(String categoriaId) {
        Optional<CategoriaEntity> categoria = buscarDetalhesCategoria(categoriaId);
        if (categoria.isEmpty()) {
            throw new CardapioNotFoundException();
        }
        CategoriaEntity categoriaEncontrado = categoria.get();
        categoriaEncontrado.setAtivo(false);
        categoriaEncontrado.setDeletado(LocalDateTime.now());
        categoriaRepository.save(categoriaEncontrado);
    }

}

