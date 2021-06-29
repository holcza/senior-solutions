package locations;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class UpdateLocationCommand {

    @NotBlank(message = "Name can not be blank")
    private String name;

    //@Min(-90)
    //@Max(90)
    @Coordinate(coordinateType = Type.LAT)
    private double lat;

    //@Min(-180)
    //@Max(180)
    @Coordinate(coordinateType = Type.LON)
    private double lon;
}
