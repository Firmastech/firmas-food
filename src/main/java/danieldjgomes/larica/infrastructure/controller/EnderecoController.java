package danieldjgomes.larica.infrastructure.controller;

import danieldjgomes.larica.core.endereco.entity.Endereco;
import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.core.usecases.endereco.CadastrarEnderecoUseCase;
import danieldjgomes.larica.infrastructure.dto.restaurante.request.CadastrarEnderecoRequest;
import danieldjgomes.larica.infrastructure.mapper.DTOMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping(path = "api/endereco")
public class EnderecoController {

    private CadastrarEnderecoUseCase cadastrarEnderecoUseCase;
    private DTOMapper mapper;

    @PostMapping
    public ResponseEntity<Endereco> registrarRestaurante(@RequestBody @Valid CadastrarEnderecoRequest dto) {
        Endereco endereco = mapper.toEndereco(dto);
        endereco = cadastrarEnderecoUseCase.cadastrar(endereco);
        return new ResponseEntity(endereco,HttpStatus.CREATED);
    }
}
