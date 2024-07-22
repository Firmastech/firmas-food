package danieldjgomes.larica.app.usecase.token;

import danieldjgomes.larica.app.usecase.usuario.request.external.TokenAutenticacaoKeycloakModelResponseDTO;
import danieldjgomes.larica.app.usecase.token.response.TokenResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TokenMapper {

    @Mapping(source = "accessToken", target = "accessToken.valor")
    @Mapping(source = "expiresIn", target = "accessToken.expiraEm")
    @Mapping(source = "refreshToken", target = "refreshToken.valor")
    @Mapping(source = "refreshExpiresIn", target = "refreshToken.expiraEm")
    TokenResponse toLoginResponse(TokenAutenticacaoKeycloakModelResponseDTO tokenResponse);}
