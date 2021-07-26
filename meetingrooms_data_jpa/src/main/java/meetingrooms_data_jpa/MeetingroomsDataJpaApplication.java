package meetingrooms_data_jpa;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class MeetingroomsDataJpaApplication implements CommandLineRunner {

    private final MeetingRoomsRepository meetingRoomsRepository;

    public static void main(String[] args) {
        SpringApplication.run(MeetingroomsDataJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello");

        meetingRoomsRepository.save(new MeetingRoom("AB",1,2));
        meetingRoomsRepository.save(new MeetingRoom("BC",3,4));

        System.out.println(meetingRoomsRepository.findAll());

        System.out.println(meetingRoomsRepository.findAllByNameLike("A%"));

    }
}
