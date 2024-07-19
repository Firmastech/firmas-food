package danieldjgomes.larica.infrastructure.expectionHandler.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ErrorResponse {
    List<String> mensagens;
    Date timestamp;
}
