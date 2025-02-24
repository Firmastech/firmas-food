package danieldjgomes.larica.app.adapter.controller;

import danieldjgomes.larica.app.usecase.cardapio.AtualizarDescritivoCardapioUseCase;
import danieldjgomes.larica.app.usecase.cardapio.BuscarCardapiosPertencentesAoRestauranteUseCase;
import danieldjgomes.larica.app.usecase.cardapio.CriarCardapioUseCase;
import danieldjgomes.larica.app.usecase.cardapio.DesativarCardapioUseCase;
import danieldjgomes.larica.app.usecase.cardapio.request.AtualizarDescritivosCardapioRequest;
import danieldjgomes.larica.app.usecase.cardapio.request.CriarCardapioRequest;
import danieldjgomes.larica.app.usecase.cardapio.response.AtualizarCardapioResponse;
import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rest/cardapios")
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
    public ResponseEntity<Optional<AtualizarCardapioResponse>> atualizarCardapio(@PathVariable String id,
                                                                                 @RequestBody AtualizarDescritivosCardapioRequest dto) {
        Optional<AtualizarCardapioResponse> response = atualizarDescritivoCardapioUseCase.atualizarCardapio(id, dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO DANIEL PF ANALISAR O QUE VAMOS FAZER AQUI
//    @GetMapping("/restaurantes/{restauranteId}")
//    public ResponseEntity<List<CardapioResponse>> buscarCardapiosPertencentesAoRestaurante(@PathVariable String restauranteId) {
//        List<CardapioResponse> response = buscarCardapiosPertencentesAoRestaurante(restauranteId);
//        return ResponseEntity.ok(response);
//    }

    @GetMapping("{cardapioId}/restaurantes/{restauranteId}")
    public ResponseEntity<CardapioResponse> buscarDetalheCardapio(@PathVariable String cardapioId) {
        CardapioResponse response = buscarCardapiosPertencentesAoRestaurante.buscarDetalheCardapio(cardapioId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativarCardapio(@PathVariable String id) {
        desativarCardapioUseCase.desativar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
