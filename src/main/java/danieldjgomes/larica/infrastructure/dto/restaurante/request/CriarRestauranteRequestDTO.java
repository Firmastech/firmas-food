package danieldjgomes.larica.infrastructure.dto.restaurante.request;

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
    private Endereco endereco;

    @Data
    public static class Endereco {
        @NotBlank
        private String rua;

        @NotBlank
        private String numero;

        @Pattern(regexp = "^\\d{2}\\d{3}-\\d{3}$\n")
        private String cep;

        @NotBlank
        private String cidade;

        @NotBlank
        @Min(2)
        @Max(2)
        private String uf;
        private String pontoReferencia;

    }
}
