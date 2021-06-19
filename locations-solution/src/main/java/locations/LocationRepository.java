package locations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface LocationRepository {

    List<Location> locations = new ArrayList<>();

    default Optional<Location> findByName(String name){
        return locations.stream()
                .filter(e->e.getName().equals(name))
                .findFirst();
    }
}
