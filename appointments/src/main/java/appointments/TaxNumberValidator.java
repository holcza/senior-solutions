package appointments;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TaxNumberValidator implements ConstraintValidator<IsValidTaxNumber,String> {

    private int toDivide;

    @Override
    public void initialize(IsValidTaxNumber constraintAnnotation) {
        toDivide = constraintAnnotation.toDivide();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        double result = 0;
        for (int i= 0; i<9;i++){
            result += Integer.parseInt(Character.toString(s.charAt(i)))*(i+1);
        }
        return s.length()==10 && result%toDivide==Integer.parseInt(Character.toString(s.charAt(9)));
    }
}
