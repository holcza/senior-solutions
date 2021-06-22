package locations;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class LocationsService {

    private ModelMapper modelMapper;

    private List<Location> locations= Collections.synchronizedList(new ArrayList<>(List.of (
            new Location(1L,"A",23,67),
            new Location(2L,"B",56,72),
            new Location(3L,"C",4,-67)
    )));;

    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<LocationDto> getLocations(){
        Type targetListType = new TypeToken<List<LocationDto>>(){}.getType();
        return modelMapper.map(locations,targetListType);
    }
}
