package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class ActivityDao {

    EntityManagerFactory entityManagerFactory;

    public ActivityDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void saveActivity(Activity activity){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(activity);
        entityManager.getTransaction().commit();

        entityManager.close();;
    }

    public Activity findActivityById(long id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Activity result = entityManager.find(Activity.class,id);
        entityManager.close();
        return result;
    }

    public List<Activity> listActivities(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Activity> activities = entityManager.createQuery("select a from Activity a order by a.desc",Activity.class)
                .getResultList();
        entityManager.close();
        return activities;
    }


}
