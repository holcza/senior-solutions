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

        Type targetListType = new TypeToken<List<InstrumentDTO>>() {
        }.getType();

        List<Instrument> filteredInstruments = instruments.stream()
                .filter(i -> (brand.isEmpty() && price.isEmpty())
                        || (brand.isEmpty() && i.getPrice() == price.get())
                        || (price.isEmpty() && i.getBrand().equals(brand.get()))
                        || (i.getPrice() == price.get() && i.getBrand().equals(brand.get())))
                .collect(Collectors.toList());

        return modelMapper.map(filteredInstruments, targetListType);
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
        Instrument instrument = findById(id);

        return modelMapper.map(instrument, InstrumentDTO.class);
    }

    public InstrumentDTO updateInstrumentById(long id, UpdatePriceCommand command) {
        Instrument instrument = findById(id);

        if (command.getPrice() == instrument.getPrice()) {
            return modelMapper.map(instrument, InstrumentDTO.class);
        } else {
            Instrument updatedInstrument =
                    new Instrument(id, instrument.getBrand(), instrument.getType(), command.getPrice(), LocalDate.now());
            int index = instruments.indexOf(instrument);
            instruments.set(index,updatedInstrument);

            return modelMapper.map(
                    updatedInstrument
                    , InstrumentDTO.class);
        }
    }

    private Instrument findById(long id) {
        return instruments.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("ID can not be found"));
    }

    public void deleteInstrumentById(long id) {
        Instrument instrument = findById(id);

        instruments.remove(instrument);
    }
}
