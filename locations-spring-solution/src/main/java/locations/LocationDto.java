package locations;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LocationDto {

    private Long id;

    private String name;

    private double lat;

    private double lon;
}
