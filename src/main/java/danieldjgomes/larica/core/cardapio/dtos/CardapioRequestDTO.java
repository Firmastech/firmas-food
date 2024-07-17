package danieldjgomes.larica.core.cardapio.dtos;


import danieldjgomes.larica.core.culinaria.entity.Culinaria;
import danieldjgomes.larica.core.prato.entity.Prato;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardapioRequestDTO {

    @NotNull
    private Prato pratoId;

    @NotNull
    private Culinaria tipoCulinariaId;
}
