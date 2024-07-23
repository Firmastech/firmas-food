package danieldjgomes.larica.core.cardapioPrato.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RemovePratoFromCardapioRequest {

    private String cardapioId;
    private String pratoId;
}
