package activitytracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ActivityDaoIT {

    ActivityDao activityDao;

    @BeforeEach
    public void init() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        activityDao = new ActivityDao(factory);
    }

    @Test
    public void testSaveActivityAndFind() {
        Activity activity = new Activity(LocalDateTime.now(), "A", Type.BIKING);
        activityDao.saveActivity(activity);
        Activity savedActivity = activityDao.findActivityById(activity.getId());
        assertEquals("A", savedActivity.getDesc());
    }

    @Test
    public void testListActivities() {
        Activity activity1 = new Activity(LocalDateTime.now(), "A", Type.BASKETBALL);
        Activity activity2 = new Activity(LocalDateTime.now(), "B", Type.BIKING);
        activityDao.saveActivity(activity1);
        activityDao.saveActivity(activity2);
        List<Activity> activities = activityDao.listActivities();
        assertEquals("B", activities.get(1).getDesc());
    }

    @Test
    public void testUpdateActivity() {
        Activity activity = new Activity(LocalDateTime.now(), "A", Type.BIKING);
        activityDao.saveActivity(activity);
        activityDao.updateActivity(activity.getId(), "B");
        assertEquals("B", activityDao.findActivityById(activity.getId()).getDesc());
        assertNotNull(activityDao.findActivityById(activity.getId()).getUpdatedAt());

    }

    @Test
    public void testFindActivityByIdWithLabels(){
        Activity activity = new Activity(LocalDateTime.now(), "A", Type.BIKING);
        activity.setLabels(List.of("a","b"));
        activityDao.saveActivity(activity);

        Activity anotherActivity = activityDao.findActivityByIdWithLabels(activity.getId());

        assertEquals(2,anotherActivity.getLabels().size());
        assertEquals("b",anotherActivity.getLabels().get(1));
    }
}
