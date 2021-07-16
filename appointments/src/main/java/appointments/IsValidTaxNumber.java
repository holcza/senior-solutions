package appointments;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TaxNumberValidator.class)
@Target(ElementType.FIELD)
public @interface IsValidTaxNumber {

    String message() default "{javax.validation.constraints.IsValidTaxNumber.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int toDivide () default 11;
}
