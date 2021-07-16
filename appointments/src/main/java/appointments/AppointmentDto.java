package appointments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {

    private String taxNumber;

    private AppointmentTime appointmentTime;

    private String caseCode;
}
