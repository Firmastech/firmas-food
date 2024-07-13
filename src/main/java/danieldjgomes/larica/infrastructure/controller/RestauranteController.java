package danieldjgomes.larica.infrastructure.controller;

import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.core.usecases.restaurante.ConsultarRestauranteUseCase;
import danieldjgomes.larica.core.usecases.restaurante.RegistrarRestauranteUseCase;
import danieldjgomes.larica.infrastructure.dto.restaurante.request.CriarRestauranteRequestDTO;
import danieldjgomes.larica.infrastructure.DTOMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping(path = "api/restaurantes")
public class RestauranteController {

    private final RegistrarRestauranteUseCase registrarRestauranteInterador;
    private final ConsultarRestauranteUseCase consultarRestauranteUseCase;
    //private final AtualizarRestauranteUseCase atualizarRestauranteUseCase;
    //private final InativarRestaurenteUseCase inativarRestauranteUseCase;

    private DTOMapper mapper;

    @PostMapping
    public ResponseEntity criarRestaurante(@RequestBody @Valid CriarRestauranteRequestDTO dto) {
        Restaurante restaurante = mapper.toRestaurante(dto);
        registrarRestauranteInterador.registrarRestaurante(restaurante);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping
    public ResponseEntity consultarRestaurante(@RequestParam String id) {
        return ResponseEntity.ok(consultarRestauranteUseCase.consultar(id));
    }



}
