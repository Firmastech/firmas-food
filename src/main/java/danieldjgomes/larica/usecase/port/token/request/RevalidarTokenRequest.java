package danieldjgomes.larica.usecase.port.token.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RevalidarTokenRequest {

    @NotBlank
    private String token;
}
