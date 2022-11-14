package web.socketsapp.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.socketsapp.server.entitiy.Timetable;

@Repository
public interface TimetableRepo extends JpaRepository<Timetable, Long> {
}
