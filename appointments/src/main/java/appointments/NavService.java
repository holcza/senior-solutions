package appointments;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NavService {

    private List<Case> cases = List.of(
            new Case("001","Adóbevallás"),
            new Case("002","Befizetés")

    );

    private ModelMapper modelMapper;

    public NavService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<CaseDto> listCases() {
        Type targetListType = new TypeToken<List<CaseDto>>() {
        }.getType();

        return modelMapper.map(cases, targetListType);

    }

    public AppointmentDto createAppointment(CreateAppointmentCommand command) {

        Appointment appointment = new Appointment(
                command.getTaxNumber(),command.getAppointmentTime(),command.getCaseCode());
        return modelMapper.map(appointment,AppointmentDto.class);
    }

    public boolean isValidCase(String s){
        return cases.stream()
                .map(Case::getCode)
                .collect(Collectors.toList()).contains(s);
    }
}
