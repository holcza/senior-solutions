package appointments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaseDto {

    private String code;

    private String description;

    @Override
    public String toString() {
        return code + " - " + description;
    }
}
