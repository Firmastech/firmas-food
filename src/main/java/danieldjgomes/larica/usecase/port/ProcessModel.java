package danieldjgomes.larica.usecase.port;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessModel<I,O> {

    I entrada;
    O resultante;

    public ProcessModel(I entrada) {
        this.entrada = entrada;
    }
}
