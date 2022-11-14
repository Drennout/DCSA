package web.socketsapp.server.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.socketsapp.server.entitiy.Hall;

@Repository
public interface HallRepo extends JpaRepository<Hall, Long> {
}
