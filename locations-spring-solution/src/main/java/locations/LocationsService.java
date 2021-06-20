package locations;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationsService {

    private List<Location> locations;

    public LocationsService() {
        this.locations = List.of (
                new Location(1L,"A",23,67),
                new Location(2L,"B",56,72),
                new Location(3L,"C",4,-67)
        );
    }

    public List<Location> getLocations(){
        return locations;
    }
}
