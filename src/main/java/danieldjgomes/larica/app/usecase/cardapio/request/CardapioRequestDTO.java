package danieldjgomes.larica.app.usecase.cardapio.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardapioRequestDTO {

    @NotBlank
    private String nome;
    private String descricao;
}
