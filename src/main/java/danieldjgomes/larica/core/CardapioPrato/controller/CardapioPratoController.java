//package danieldjgomes.larica.core.CardapioPrato.controller;
//
//import danieldjgomes.larica.core.cardapio.dtos.request.AddPratosToCardapioRequest;
//import danieldjgomes.larica.core.cardapio.dtos.request.RemoverPratoRequestDTO;
//import danieldjgomes.larica.core.usecases.CardapioPratoUseCase;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/cardapio-prato")
//@AllArgsConstructor
//public class CardapioPratoController {
//
//    private final CardapioPratoUseCase cardapioPratoUseCase;
//
//    @PostMapping("/{cardapioId}")
//    public ResponseEntity<Void> adicionarPratos(@PathVariable String cardapioId, @RequestBody AddPratosToCardapioRequest dto) {
//        cardapioPratoUseCase.adicionarPratos(cardapioId, dto);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/remover-prato")
//    public ResponseEntity<Void> removerPrato(@RequestBody RemoverPratoRequestDTO dto) {
//        cardapioPratoUseCase.removerPrato(dto.getCardapioId(), dto.getPratoId());
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//}
