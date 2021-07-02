package org.training360.musicstore;

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
@RequestMapping("/api/instruments")
public class MusicController {

    private MusicStoreService musicStoreService;

    public MusicController(MusicStoreService musicStoreService) {
        this.musicStoreService = musicStoreService;
    }

    @GetMapping
    public List<InstrumentDTO> getInstruments(@RequestParam Optional<String> brand, @RequestParam Optional<Double> price){
        return musicStoreService.getInstruments(brand,price);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InstrumentDTO createInstrument(@Valid @RequestBody CreateInstrumentCommand command){
        return musicStoreService.createInstrument(command);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(){
        musicStoreService.delete();
    }

    @GetMapping("/{id}")
    public InstrumentDTO getInstrumentById (@PathVariable("id") long id){
        return musicStoreService.getInstrumentById(id);
    }

    @PutMapping("/{id}")
    public InstrumentDTO updateInstrumentById (@PathVariable("id") long id, @Valid @RequestBody UpdatePriceCommand command){
        return musicStoreService.updateInstrumentById(id,command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInstrumentById (@PathVariable("id") long id){
        musicStoreService.deleteInstrumentById(id);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Problem> handleExceptionWhenNotFound (IllegalArgumentException iae){
        Problem problem = Problem.builder()
                .withType(URI.create("instruments/not-found"))
                .withTitle("Not found instrument")
                .withStatus(Status.NOT_FOUND)
                .withDetail(iae.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }
}
