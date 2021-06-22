package locations;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LocationsService {

    private ModelMapper modelMapper;

    private List<Location> locations = Collections.synchronizedList(new ArrayList<>(List.of (
            new Location(1L,"A",23,67),
            new Location(2L,"B",56,72),
            new Location(3L,"C",4,-67)
    )));

    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

    }

    public List<LocationDto> getLocations(Optional<String> name){
        Type targetListType = new TypeToken<List<LocationDto>>(){}.getType();
        List<Location> locationsFiltered = locations.stream()
                .filter(location -> name.isEmpty() || location.getName().equals(name))
                .collect(Collectors.toList());
        return modelMapper.map(locationsFiltered,targetListType);
    }

    public LocationDto findLocationById(long id) {
        Location location = locations.stream()
                .filter(l -> l.getId()==id)
                .findAny()
                .orElseThrow(()->new IllegalArgumentException("Can not find Id "+id));

        return modelMapper.map(location,LocationDto.class);
    }
}
