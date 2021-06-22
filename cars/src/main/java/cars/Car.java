package cars;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Car {

    private String brand;

    private String type;

    private int age;

    private CarStatus carStatus;

    private List<KmState> kmStateList;
}
