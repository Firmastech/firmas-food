package danieldjgomes.larica.infrastructure.keycloak.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TokenAutenticacaoKeycloakModelResponseDTO {

    private String accessToken;
    private Integer expiresIn;
    private Integer refreshExpiresIn;
    private String refreshToken;
    private String tokenType;
    private Integer notBeforePolicy;
    private String sessionState;
    private String scope;
}
