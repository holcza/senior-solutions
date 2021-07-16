package appointments;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NavController {

    private NavService navService;

    public NavController(NavService navService) {
        this.navService = navService;
    }

    @GetMapping("/types")
    public List<CaseDto> listCases(){
        return navService.listCases();
    }

    @PostMapping("/appointments")
    public AppointmentDto createAppointment(@Valid @RequestBody CreateAppointmentCommand command){
        return navService.createAppointment(command);
    }
}
