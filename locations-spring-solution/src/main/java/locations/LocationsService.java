package locations;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class LocationsService {

    private ModelMapper modelMapper;

    private AtomicLong idGenerator = new AtomicLong();

    private List<Location> locations = Collections.synchronizedList(new ArrayList<>(List.of(
            new Location(idGenerator.incrementAndGet(), "A", 23, 67),
            new Location(idGenerator.incrementAndGet(), "B", 56, 72),
            new Location(idGenerator.incrementAndGet(), "C", 4, -67)
    )));

    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

    }

    public List<LocationDto> getLocations(Optional<String> name) {
        Type targetListType = new TypeToken<List<LocationDto>>() {
        }.getType();
        List<Location> locationsFiltered = locations.stream()
                .filter(location -> name.isEmpty() || location.getName().equals(name.get()))
                .collect(Collectors.toList());
        return modelMapper.map(locationsFiltered, targetListType);
    }

    public LocationDto findLocationById(long id) {
        Location location = locations.stream()
                .filter(l -> l.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Can not find Id " + id));

        return modelMapper.map(location, LocationDto.class);
    }

    public LocationDto createLocation(CreateLocationCommand command) {
        Location location = new Location(idGenerator.incrementAndGet(), command.getName()
                , command.getLat(), command.getLon());

        locations.add(location);

        return modelMapper.map(location,LocationDto.class);
    }

    public LocationDto updateLocation(long id, UpdateLocationCommand command) {

        Location location = locations.stream()
                .filter(l->l.getId()==id)
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("Can not find location id "+ id));
        location.setName(command.getName());
        location.setLat(command.getLat());
        location.setLon(command.getLon());
        return modelMapper.map(location,LocationDto.class);
    }

    public void deleteLocation(long id) {
        Location location = locations.stream()
                .filter(l->l.getId()==id)
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("Can not find location id "+ id));
        locations.remove(location);

    }

}
