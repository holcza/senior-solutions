package locations;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LocationService {

    public void writeLocations(Path file, List<Location> locations) {

        try (FileWriter fileWriter = new FileWriter(String.valueOf(file))) {
            for (Location l : locations) {
                fileWriter.write(l.getName() + "," + l.getLat() + "," + l.getLon() + "/n");
            }

        } catch (IOException ioe) {
            throw new IllegalStateException("Can not write file", ioe);
        }

    }

    public List<Location> readLocationsFromCsv(Path path) {
        List<Location> locations = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(path);

            for (String l : lines) {
                String[] parts = l.split(",");
                locations.add(new Location(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2])));
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file", ioe);
        }
        return locations;
    }
}
