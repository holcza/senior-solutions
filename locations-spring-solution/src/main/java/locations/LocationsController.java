package locations;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locations")
public class LocationsController {

    LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }


    @GetMapping
    public List<LocationDto> getLocations(@RequestParam Optional<String> name){
        return locationsService.getLocations(name);
    }

    //@GetMapping("/{id}")
    //public ResponseEntity findLocationById (@PathVariable("id") long id){
    //    try{
    //        return ResponseEntity.ok(locationsService.findLocationById(id));
    //    } catch (IllegalArgumentException iae){
    //        return ResponseEntity.notFound().build();
    //    }
    //}

    @GetMapping("/{id}")
    public LocationDto findLocationById (@PathVariable("id") long id){
        return locationsService.findLocationById(id);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LocationDto createLocation (@Valid @RequestBody CreateLocationCommand command){
        return locationsService.createLocation(command);
    }

    @PutMapping("/{id}")
    public LocationDto updateLocation(@PathVariable("id") long id,@Valid @RequestBody UpdateLocationCommand command){
        return locationsService.updateLocation(id,command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocation (@PathVariable("id") long id){
        locationsService.deleteLocation(id);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> locationNotFoundException(IllegalArgumentException iae){
        Problem problem = Problem.builder()
                .withType(URI.create("/locations/not_found"))
                .withTitle("Not found")
                .withStatus(Status.NOT_FOUND)
                .withDetail(iae.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);

    }
}
