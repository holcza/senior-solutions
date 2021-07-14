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

    public void saveActivity(Activity activity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        activity.setCreatedAt(LocalDateTime.now());
        entityManager.persist(activity);
        entityManager.getTransaction().commit();

        entityManager.close();
        ;
    }

    public Activity findActivityById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Activity result = entityManager.find(Activity.class, id);
        entityManager.close();
        return result;
    }

    public List<Activity> listActivities() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Activity> activities = entityManager.createQuery("select a from Activity a order by a.desc", Activity.class)
                .getResultList();
        entityManager.close();
        return activities;
    }

    public void updateActivity(long id, String desc) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Activity modifiedActivity = entityManager.find(Activity.class, id);
        modifiedActivity.setDesc(desc);
        modifiedActivity.setUpdatedAt(LocalDateTime.now());
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Activity findActivityByIdWithLabels(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Activity activity = entityManager.createQuery(
                "select a from Activity a join fetch a.labels where a.id=:id", Activity.class)
                .setParameter("id", id)
                .getSingleResult();
        entityManager.close();
        return activity;
    }

    public Activity findActivityByIdWithTrackPoints(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Activity activity = entityManager.createQuery(
                "select a from Activity a join fetch a.trackPoints where a.id=:id", Activity.class)
                .setParameter("id", id)
                .getSingleResult();
        entityManager.close();
        return activity;
    }

    public List<Coordinate> findTrackPointCoordinatesByDate(LocalDateTime afterThis, int start, int max){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Coordinate> coordinates = entityManager.createNamedQuery("findCoordinates",Coordinate.class)
                .setParameter("afterThis",afterThis)
                .setFirstResult(start)
                .setMaxResults(max)
                .getResultList();

        entityManager.close();

        return coordinates;

    }


}
