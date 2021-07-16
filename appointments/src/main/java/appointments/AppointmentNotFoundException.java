package appointments;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class AppointmentNotFoundException extends AbstractThrowableProblem {

    public AppointmentNotFoundException() {
        super(URI.create("appointments/not-found"),
                "Not found",
                Status.NOT_FOUND,
                "Appointment not found");
    }
}
