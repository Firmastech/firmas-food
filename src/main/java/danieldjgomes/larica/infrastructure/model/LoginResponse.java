package danieldjgomes.larica.infrastructure.model;

import lombok.Data;

@Data
public class LoginResponse {

    private Token accessToken;
    private Token refreshToken;

    @Data
    public static class Token {
        private String valor;
        private Long expiraEm;
    }
}
