package danieldjgomes.larica.usecase.pedido.validation;

import danieldjgomes.larica.usecase.pedido.request.ProcessarPedidoRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DataHoraEntregaMaiorQueDataHoraPedidoValidator implements ConstraintValidator<DataHoraEntregaMaiorQueDataHoraPedido, ProcessarPedidoRequest> {

    @Override
    public void initialize(DataHoraEntregaMaiorQueDataHoraPedido constraintAnnotation) {
    }

    @Override
    public boolean isValid(ProcessarPedidoRequest processarPedidoRequest, ConstraintValidatorContext context) {
        if (processarPedidoRequest.getDataHoraEntrega() == null || processarPedidoRequest.getDataHoraPedido() == null) {
            return true;
        }
        return processarPedidoRequest.getDataHoraEntrega().isAfter(processarPedidoRequest.getDataHoraPedido());
    }
}