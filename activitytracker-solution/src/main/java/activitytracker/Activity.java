package activitytracker;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "activities")
public class Activity {

    @TableGenerator(name = "activity_gen",
            table = "act_id_gen",
            pkColumnName = "id_gen",
            valueColumnName = "id_val")
    @Id
    @GeneratedValue(generator = "activity_gen")
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "description", nullable = false, length = 200)
    private String desc;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Type type;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ElementCollection
    @CollectionTable(name = "labels",joinColumns = @JoinColumn(name = "activity_id"))
    @Column(name = "label")
    private List<String> labels;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "activity")
    @OrderBy("time")
    private List<TrackPoint> trackPoints;

    public Activity() {
    }

    public Activity(LocalDateTime startTime, String desc, Type type) {
        this.startTime = startTime;
        this.desc = desc;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public void addTrackPoint (TrackPoint trackPoint){
        if (trackPoints == null){
            trackPoints = new ArrayList<>();
        }

        trackPoints.add(trackPoint);
        trackPoint.setActivity(this);
    }
}
