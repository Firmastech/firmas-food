package danieldjgomes.larica.infrastructure;

import danieldjgomes.larica.infrastructure.model.LoginResponse;
import org.keycloak.representations.AccessTokenResponse;
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
