package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class ActivityTrackerMain {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Activity activity1 = new Activity(LocalDateTime.now(), "A", Type.BASKETBALL);
        Activity activity2 = new Activity(LocalDateTime.now(), "B", Type.BIKING);
        entityManager.persist(activity1);
        entityManager.persist(activity2);
        entityManager.getTransaction().commit();

        entityManager.close();
        ;
        entityManagerFactory.close();
    }
}
