package danieldjgomes.larica.usecase.pedido.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = DataHoraEntregaMaiorQueDataHoraPedidoValidator.class)
@Documented
public @interface DataHoraEntregaMaiorQueDataHoraPedido {

    String message() default "A data e hora da entrega deve ser maior que a dataHoraPedido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}