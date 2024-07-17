package danieldjgomes.larica.usecase.pedido.validation;

import danieldjgomes.larica.usecase.pedido.request.ProcessarPedidoRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class TotalCorrespondenteValidator implements ConstraintValidator<TotalCorrespondente, ProcessarPedidoRequest> {

    @Override
    public void initialize(TotalCorrespondente constraintAnnotation) {
    }

    @Override
    public boolean isValid(ProcessarPedidoRequest processarPedidoRequest, ConstraintValidatorContext context) {
        if (processarPedidoRequest.getItensList() == null || processarPedidoRequest.getTotal() == null) {
            return true;
        }
        BigDecimal totalItens = processarPedidoRequest.getItensList().stream()
                .map(item -> item.getPrecoUnitario().multiply(new BigDecimal(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return processarPedidoRequest.getTotal().compareTo(totalItens) == 0;
    }
}