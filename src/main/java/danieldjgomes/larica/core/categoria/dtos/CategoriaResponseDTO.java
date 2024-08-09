package danieldjgomes.larica.core.categoria.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponseDTO {

    private String id;
    private String nome;
    private String restauranteId;
    private LocalDateTime criado;
    private LocalDateTime atualizado;
    private Boolean estaAtivo;
    private LocalDateTime deletado;
}
