package appointments;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.stream.Collectors;

public class CaseValidator implements ConstraintValidator<IsValidCase,String> {

    private NavService navService;

    public CaseValidator(NavService navService) {
        this.navService = navService;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return navService.isValidCase(s);
    }
}
