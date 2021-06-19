package locations;

import java.util.ArrayList;
import java.util.List;

public class LocationOperators {

    public List<Location> filterOnNorth(List<Location> locations) {
        List<Location> locationsAtNorth = new ArrayList<>();

        for (Location l : locations) {
            if (l.getLat() > 0) {
                locationsAtNorth.add(l);
            }
        }

        return locationsAtNorth;
    }
}
