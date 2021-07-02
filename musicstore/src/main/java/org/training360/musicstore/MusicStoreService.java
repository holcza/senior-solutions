package org.training360.musicstore;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MusicStoreService {

    private List<Instrument> instruments = new ArrayList<>();

    private AtomicLong idGenerator = new AtomicLong();

    private ModelMapper modelMapper;

    public MusicStoreService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<InstrumentDTO> getInstruments(Optional<String> brand, Optional<Double> price) {

        return instruments.stream()
                .filter(i -> brand.isEmpty() || i.getBrand().equalsIgnoreCase(brand.get()))
                .filter(i -> price.isEmpty() || i.getPrice() == price.get())
                .map(i -> modelMapper.map(i, InstrumentDTO.class))
                .collect(Collectors.toList());

    }

    public InstrumentDTO createInstrument(CreateInstrumentCommand command) {

        Instrument instrument =
                new Instrument(
                        idGenerator.incrementAndGet(), command.getBrand(), command.getType(), command.getPrice(), LocalDate.now());

        instruments.add(instrument);

        return modelMapper.map(instrument, InstrumentDTO.class);
    }

    public void delete() {
        instruments.clear();
        idGenerator.set(0);
    }

    public InstrumentDTO getInstrumentById(long id) {

        return modelMapper.map(findById(id), InstrumentDTO.class);
    }

    public InstrumentDTO updateInstrumentById(long id, UpdatePriceCommand command) {
        Instrument instrument = findById(id);

        if (command.getPrice() != instrument.getPrice()) {
            instrument.setPrice(command.getPrice());
            instrument.setPostDate(LocalDate.now());
        }

        return modelMapper.map(instrument, InstrumentDTO.class);
    }


    private Instrument findById(long id) {
        return instruments.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("ID can not be found"));
    }

    public void deleteInstrumentById(long id) {

        instruments.remove(findById(id));
    }
}
