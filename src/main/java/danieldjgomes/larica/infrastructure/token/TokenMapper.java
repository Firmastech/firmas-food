package danieldjgomes.larica.infrastructure.token;

import danieldjgomes.larica.infrastructure.keycloak.dto.TokenAutenticacaoKeycloakModelResponseDTO;
import danieldjgomes.larica.usecase.port.token.request.LoginUsuarioRequest;
import danieldjgomes.larica.usecase.port.token.response.TokenResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TokenMapper {

    @Mapping(source = "accessToken", target = "accessToken.valor")
    @Mapping(source = "expiresIn", target = "accessToken.expiraEm")
    @Mapping(source = "refreshToken", target = "refreshToken.valor")
    @Mapping(source = "refreshExpiresIn", target = "refreshToken.expiraEm")
    TokenResponse toLoginResponse(TokenAutenticacaoKeycloakModelResponseDTO tokenResponse);}
