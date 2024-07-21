package danieldjgomes.larica.infrastructure.expectionHandler.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class ErrorResponse {
    List<String> mensagens;
    Date timestamp;
}
