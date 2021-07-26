package meetingrooms;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MeetingRoomsRepositoryIT {

    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");

    static MeetingRoomsRepository repo = new MeetingRoomsRepository(factory);

    @BeforeEach
    void emptyTable(){
        repo.deleteAll();
    }

    @AfterAll
    static void closeFactory(){
        factory.close();
    }

    @Test
    @DisplayName("Save two meeting rooms then list all")
    void testSaveThenList() {
        repo.save("A", 1, 2);
        repo.save("B", 3, 4);
        List<String> names = repo.getMeetingroomsOrderedByName();
        assertEquals(List.of("A", "B"), names);
    }
}
