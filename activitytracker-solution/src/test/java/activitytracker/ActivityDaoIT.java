package activitytracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
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
    public void testFindActivityByIdWithLabels() {
        Activity activity = new Activity(LocalDateTime.now(), "A", Type.BIKING);
        activity.setLabels(List.of("a", "b"));
        activityDao.saveActivity(activity);

        Activity anotherActivity = activityDao.findActivityByIdWithLabels(activity.getId());

        assertEquals(2, anotherActivity.getLabels().size());
        assertEquals("b", anotherActivity.getLabels().get(1));
    }

    @Test
    public void testFindActivityByIdWithTrackPoints() {
        Activity activity = new Activity(LocalDateTime.now(), "A", Type.BIKING);
        TrackPoint trackPoint1 = new TrackPoint(LocalDate.of(2020, 10, 20), 45, 67);
        TrackPoint trackPoint2 = new TrackPoint(LocalDate.of(2020, 10, 30), 50, 67);
        activity.addTrackPoint(trackPoint1);
        activity.addTrackPoint(trackPoint2);
        activityDao.saveActivity(activity);

        Activity anotherActivity = activityDao.findActivityByIdWithTrackPoints(activity.getId());

        assertEquals(2, anotherActivity.getTrackPoints().size());
        assertEquals(50, anotherActivity.getTrackPoints().get(1).getLat());
    }

    @Test
    public void testFindTrackPointCoordinatesByDate() {
        Activity activity1 = new Activity(
                LocalDateTime.of(2020, 10, 21, 3, 0), "A", Type.BIKING);
        Activity activity2 = new Activity(
                LocalDateTime.of(2020, 10, 21, 5, 0), "B", Type.BIKING);
        Activity activity3 = new Activity(
                LocalDateTime.of(2020, 10, 21, 6, 0), "C", Type.BIKING);
        TrackPoint trackPoint1 = new TrackPoint(LocalDate.of(2020, 10, 20), 45, 67);
        TrackPoint trackPoint2 = new TrackPoint(LocalDate.of(2020, 10, 30), 50, 70);
        TrackPoint trackPoint3 = new TrackPoint(LocalDate.of(2020, 11, 30), 60, 80);
        TrackPoint trackPoint4 = new TrackPoint(LocalDate.of(2020, 11, 30), 70, 90);
        TrackPoint trackPoint5 = new TrackPoint(LocalDate.of(2020, 11, 30), 80, 100);
        activity1.addTrackPoint(trackPoint1);
        activity2.addTrackPoint(trackPoint2);
        activity2.addTrackPoint(trackPoint3);
        activity3.addTrackPoint(trackPoint4);
        activity3.addTrackPoint(trackPoint5);
        activityDao.saveActivity(activity1);
        activityDao.saveActivity(activity2);
        activityDao.saveActivity(activity3);

        List<Coordinate> coordinates = activityDao.findTrackPointCoordinatesByDate(
                LocalDateTime.of(2020, 10, 21, 4, 0), 2, 2);

        assertEquals(2,coordinates.size());
        assertEquals(90,coordinates.get(1).getLon());

    }
}
