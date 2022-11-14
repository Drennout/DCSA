package web.socketsapp.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.socketsapp.server.entitiy.Table;

@Repository
public interface TableRepo extends JpaRepository<Table, Long> {
}
