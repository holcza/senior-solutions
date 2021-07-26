package meetingrooms;


import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class MeetingRoomsRepository {

    private EntityManagerFactory factory;

    MeetingRoom save(String name, int width, int length) {
        MeetingRoom meetingRoom = new MeetingRoom(name, width, length);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(meetingRoom);
        em.getTransaction().commit();

        em.close();
        return meetingRoom;
    }

    List<String> getMeetingroomsOrderedByName() {
        EntityManager em = factory.createEntityManager();
        ;
        List<String> names = em.createNamedQuery("getAllOrderByName", String.class)
                .getResultList();

        em.close();
        return names;
    }

    void deleteAll(){
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete from MeetingRoom mr").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

}
