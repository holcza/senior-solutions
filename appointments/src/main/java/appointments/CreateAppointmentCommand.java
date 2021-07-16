package appointments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAppointmentCommand {

    @IsValidTaxNumber
    private String taxNumber;

    @IsValidTime
    private AppointmentTime appointmentTime;

    @IsValidCase
    private String caseCode;
}
