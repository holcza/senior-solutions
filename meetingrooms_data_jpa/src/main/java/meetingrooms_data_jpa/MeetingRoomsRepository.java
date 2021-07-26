package meetingrooms_data_jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRoomsRepository extends JpaRepository<MeetingRoom,Long> {

    //select mr from MeetingRoom where mr.name like :name
    List<MeetingRoom> findAllByNameLike(String prefix);
}
