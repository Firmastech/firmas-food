package danieldjgomes.larica.infrastructure.token.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RevalidarTokenRequest {

    @NotBlank
    private String token;
}
