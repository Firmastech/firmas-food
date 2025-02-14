package danieldjgomes.larica.infrastructure;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;

@Data
public class CriarRestauranteDTO {

    @Min(2)
    private String nome;
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
