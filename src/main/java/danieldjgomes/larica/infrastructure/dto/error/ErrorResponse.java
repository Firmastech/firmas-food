package danieldjgomes.larica.infrastructure.dto.error;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private Long id;
    private String message;
    private LocalDateTime timestamp;

}
