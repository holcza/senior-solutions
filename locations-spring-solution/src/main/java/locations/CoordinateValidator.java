package locations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CoordinateValidator implements ConstraintValidator<Coordinate,String> {

    private Type coordinateType;

    @Override
    public void initialize(Coordinate constraintAnnotation) {
        coordinateType = constraintAnnotation.coordinateType();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        double valueInDouble = Double.parseDouble(value);
        if (coordinateType.equals(Type.LAT)){
            return valueInDouble>=-90 &&
                    valueInDouble<=90;
        } else {
            return valueInDouble>=-180 &&
                    valueInDouble<=180;
        }
    }
}
