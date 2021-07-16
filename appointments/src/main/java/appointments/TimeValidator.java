package appointments;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class TimeValidator implements ConstraintValidator<IsValidTime,AppointmentTime> {

    @Override
    public boolean isValid(AppointmentTime appointmentTime, ConstraintValidatorContext constraintValidatorContext) {
        return appointmentTime.getStartTime().isAfter(LocalDateTime.now())
                &&appointmentTime.getEndTime().isAfter(LocalDateTime.now())
                &&appointmentTime.getEndTime().isAfter(appointmentTime.getStartTime());
    }
}
