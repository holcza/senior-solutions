package appointments;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CaseValidator.class)
@Target(ElementType.FIELD)
public @interface IsValidCase {

    String message() default "{javax.validation.constraints.IsValidCase.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
