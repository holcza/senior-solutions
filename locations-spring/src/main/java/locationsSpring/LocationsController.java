package locationsSpring;

import locationsSpring.Location;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LocationsController {

    private List<Location> locations;

    public LocationsController(List<Location> locations) {
        this.locations = locations;
    }

    @GetMapping("/")
    @ResponseBody
    public List<Location> getLocations() {
        return locations;
    }

}
