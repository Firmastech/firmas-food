package danieldjgomes.larica.infrastructure.controller;

import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.core.usecases.restaurante.AtualizarRestauranteUseCase;
import danieldjgomes.larica.core.usecases.restaurante.ConsultarRestauranteUseCase;
import danieldjgomes.larica.core.usecases.restaurante.InativarRestaurenteUseCase;
import danieldjgomes.larica.core.usecases.restaurante.RegistrarRestauranteUseCase;
import danieldjgomes.larica.infrastructure.dto.restaurante.request.AtualizarRestauranteRequestDTO;
import danieldjgomes.larica.infrastructure.dto.restaurante.request.CriarRestauranteRequestDTO;
import danieldjgomes.larica.infrastructure.mapper.DTOMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping(path = "api/restaurantes")
public class RestauranteController {

    private final RegistrarRestauranteUseCase registrarRestauranteInterador;
    private final ConsultarRestauranteUseCase consultarRestauranteUseCase;
    private final AtualizarRestauranteUseCase atualizarRestauranteUseCase;
    private final InativarRestaurenteUseCase inativarRestauranteUseCase;
    private DTOMapper mapper;

    @PostMapping
    public ResponseEntity criarRestaurante(@RequestBody @Valid CriarRestauranteRequestDTO dto) {
        Restaurante restaurante = mapper.toRestaurante(dto);
        registrarRestauranteInterador.registrarRestaurante(restaurante);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping
    public ResponseEntity<Restaurante> consultarRestaurante(@RequestParam String id) {
        return ResponseEntity.ok(consultarRestauranteUseCase.consultar(UUID.fromString(id)));

    }

    @PutMapping
    public ResponseEntity atualizarRestaurante(@RequestBody @Valid AtualizarRestauranteRequestDTO dto) {
        Restaurante restaurante = mapper.toRestaurante(dto);
        atualizarRestauranteUseCase.update(restaurante);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping
    public ResponseEntity inativarRestaurante(@RequestBody String id) {
        inativarRestauranteUseCase.inativarRestaurante(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}