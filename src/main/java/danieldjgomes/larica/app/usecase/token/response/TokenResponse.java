package danieldjgomes.larica.app.usecase.token.response;

import lombok.Data;

@Data
public class TokenResponse {

    private Token accessToken;
    private Token refreshToken;

    @Data
    public static class Token {
        private String valor;
        private Long expiraEm;
    }
}
