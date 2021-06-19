package locations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationsController {

    List<Location> locationList;

    public LocationsController(List<Location> locationList) {
        this.locationList = locationList;
    }

    @GetMapping("/locations")
    public String getLocations(){
        return locationList.toString();
    }
}
