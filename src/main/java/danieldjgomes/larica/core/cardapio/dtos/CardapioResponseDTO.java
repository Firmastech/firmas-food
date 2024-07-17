package danieldjgomes.larica.core.cardapio.dtos;

import danieldjgomes.larica.core.culinaria.dtos.CulinariaResponseDTO;
import danieldjgomes.larica.core.prato.dtos.PratoResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardapioResponseDTO {

    private UUID cardapioId;
    private PratoResponseDTO prato;
    private CulinariaResponseDTO tipoCulinaria;
}
