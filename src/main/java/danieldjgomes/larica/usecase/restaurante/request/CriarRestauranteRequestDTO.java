package danieldjgomes.larica.usecase.restaurante.request;

import danieldjgomes.larica.core.restaurante.entity.enums.StatusFuncionamento;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CriarRestauranteRequestDTO {

    @NotBlank
    private String nome;

    @NotNull
    private Integer tempoEstimadoDeEntrega;

    private StatusFuncionamento statusFuncionamento = StatusFuncionamento.INATIVO;

    @NotNull
    private CadastrarEnderecoRequest endereco;

}
