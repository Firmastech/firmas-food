package danieldjgomes.larica.app.adapter.controller;

import danieldjgomes.larica.app.usecase.cardapio.AtualizarDescritivoCardapioUseCase;
import danieldjgomes.larica.app.usecase.cardapio.BuscarCardapiosPertencentesAoRestauranteUseCase;
import danieldjgomes.larica.app.usecase.cardapio.CriarCardapioUseCase;
import danieldjgomes.larica.app.usecase.cardapio.DesativarCardapioUseCase;
import danieldjgomes.larica.app.usecase.cardapio.request.AtualizarDescritivosCardapioRequest;
import danieldjgomes.larica.app.usecase.cardapio.request.CriarCardapioRequest;
import danieldjgomes.larica.app.usecase.cardapio.response.AtualizarCardapioResponse;
import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;
import danieldjgomes.larica.app.usecase.cardapio.response.ResumoCardapioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cardapios")
@RequiredArgsConstructor
public class CardapioController {
    private final CriarCardapioUseCase criarCardapioUseCase;
    private final AtualizarDescritivoCardapioUseCase atualizarDescritivoCardapioUseCase;
    private final BuscarCardapiosPertencentesAoRestauranteUseCase buscarCardapiosPertencentesAoRestaurante;
    private final DesativarCardapioUseCase desativarCardapioUseCase;

    @PostMapping
    public ResponseEntity<CardapioResponse> criarCardapio(@RequestBody CriarCardapioRequest request) {
        CardapioResponse response = criarCardapioUseCase.criar(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtualizarCardapioResponse> atualizarCardapio(@PathVariable String id, @RequestBody AtualizarDescritivosCardapioRequest dto) {
        AtualizarCardapioResponse response = atualizarDescritivoCardapioUseCase.atualizarCardapio(id, dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/restaurantes/{restauranteId}")
    public ResponseEntity<List<ResumoCardapioResponse>> buscarCardapiosPertencentesAoRestaurante(@PathVariable String restauranteId) {
        List<ResumoCardapioResponse> response = buscarCardapiosPertencentesAoRestaurante.buscarCardapio(restauranteId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{cardapioId}/restaurantes/{restauranteId}")
    public ResponseEntity<CardapioResponse> buscarDetalheCardapio(@PathVariable String cardapioId, @PathVariable String restauranteId) {
        CardapioResponse response = buscarCardapiosPertencentesAoRestaurante.buscarDetalheCardapio(cardapioId, restauranteId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativarCardapio(@PathVariable String id) {
        desativarCardapioUseCase.desativar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
