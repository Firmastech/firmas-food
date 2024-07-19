package danieldjgomes.larica.core.culinaria.controller;

import danieldjgomes.larica.core.culinaria.dtos.CulinariaRequestDTO;
import danieldjgomes.larica.core.culinaria.dtos.CulinariaResponseDTO;
import danieldjgomes.larica.core.culinaria.service.CulinariaServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/culinaria")
@RequiredArgsConstructor
public class CulinariaController {

    private final CulinariaServiceImpl culinariaService;

    @PostMapping("/create")
    public ResponseEntity<CulinariaResponseDTO> createCulinaria(@RequestBody @Valid CulinariaRequestDTO request) {
        CulinariaResponseDTO createdCulinaria = culinariaService.createCulinaria(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCulinaria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CulinariaResponseDTO> getCulinariaById(@PathVariable String id) {
        Optional<CulinariaResponseDTO> culinaria = culinariaService.getCulinariaById(id);
        return culinaria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CulinariaResponseDTO>> listAllCulinarias() {
        List<CulinariaResponseDTO> culinarias = culinariaService.listAllCulinarias();
        return new ResponseEntity<>(culinarias, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CulinariaResponseDTO> updateCulinaria(@PathVariable String id, @RequestBody @Valid CulinariaRequestDTO request) {
        Optional<CulinariaResponseDTO> culinaria = culinariaService.updateCulinaria(id, request);
        return culinaria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCulinaria(@PathVariable String id) {
        culinariaService.deleteCulinaria(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
