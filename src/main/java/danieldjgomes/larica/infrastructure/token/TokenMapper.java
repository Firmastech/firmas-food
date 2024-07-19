package danieldjgomes.larica.infrastructure.token;

import danieldjgomes.larica.infrastructure.TokenKeycloakAutenticacao;
import danieldjgomes.larica.infrastructure.token.model.LoginResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TokenMapper {

    @Mapping(source = "accessToken", target = "accessToken.valor")
    @Mapping(source = "expiresIn", target = "accessToken.expiraEm")
    @Mapping(source = "refreshToken", target = "refreshToken.valor")
    @Mapping(source = "refreshExpiresIn", target = "refreshToken.expiraEm")
    LoginResponse toLoginResponse(TokenKeycloakAutenticacao tokenResponse);
}
