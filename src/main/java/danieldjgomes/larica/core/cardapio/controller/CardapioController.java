package danieldjgomes.larica.core.cardapio.controller;

import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.core.cardapio.dtos.request.CardapioRequestDTO;
import danieldjgomes.larica.core.cardapio.dtos.request.CardapioUpdateRequestDTO;
import danieldjgomes.larica.core.cardapio.dtos.response.CardapioResponseDTO;
import danieldjgomes.larica.core.usecases.CardapioUseCase;
import danieldjgomes.larica.infrastructure.AuthorizationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
@RequestMapping("/rest/cardapios")
@AllArgsConstructor
public class CardapioController {

    private final CardapioUseCase cardapioUseCase;

    @PostMapping
    public ResponseEntity<CardapioResponseDTO> criarCardapio(@RequestBody @Valid CardapioRequestDTO dto) {
        UsuarioEntity usuario = AuthorizationService.findUsuario();
        CardapioResponseDTO response = cardapioUseCase.criarCardapio(dto, usuario);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardapioResponseDTO> atualizarCardapio(@PathVariable String id, @RequestBody CardapioUpdateRequestDTO dto) {
        CardapioResponseDTO response = cardapioUseCase.atualizarCardapio(id, dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativarCardapio(@PathVariable String id) {
        cardapioUseCase.desativarCardapio(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
