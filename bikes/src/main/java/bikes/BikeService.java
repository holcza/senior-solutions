package bikes;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BikeService {

    private List<Bike> bikes = new ArrayList<>();

    public List<Bike> getBikes(){

        if (bikes.isEmpty()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("bikes.csv"));

                parseBike(reader);

            } catch (IOException ioe) {
                throw new IllegalStateException("Can not read file");
            }
        }

        return bikes;
    }

    private void parseBike(BufferedReader reader) throws IOException {
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            bikes.add(new Bike(
                    parts[0],parts[1],LocalDateTime.parse(parts[2],formatter),Double.parseDouble(parts[3])
            ));
        }
    }

    public List<String> getUsers() {
        if (bikes.isEmpty()) {
            getBikes();
        }

        return getUsersFromMemory();
    }

    private List<String> getUsersFromMemory() {

        return bikes.stream()
                .map(Bike::getLastUser)
                .collect(Collectors.toList());
    }
}
