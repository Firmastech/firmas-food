package danieldjgomes.larica.infrastructure.dto.restaurante.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EnderecoDTO {

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
