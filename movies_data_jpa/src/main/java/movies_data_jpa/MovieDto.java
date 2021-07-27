package movies_data_jpa;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

    private String title;

    private List<Integer> ratings;
}
