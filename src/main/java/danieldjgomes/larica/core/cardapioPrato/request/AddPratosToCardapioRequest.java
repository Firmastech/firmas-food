package danieldjgomes.larica.core.cardapioPrato.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddPratosToCardapioRequest {

    @NotNull
    private String cardapioId;

    @NotNull
    private List<String> pratoIds;
}
