package locations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LocationService {

    public List<Location> readLocationsFromCsv (Path path) throws IOException {
        List<Location> locations = new ArrayList<>();

        Files.readString(path).split(",");
        return locations;
    }
}
