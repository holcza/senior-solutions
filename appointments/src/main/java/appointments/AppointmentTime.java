package appointments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class AppointmentTime {

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
