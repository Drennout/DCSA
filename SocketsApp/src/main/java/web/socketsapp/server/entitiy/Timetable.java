package web.socketsapp.server.entitiy;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table (name = "timetable")
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private List<web.socketsapp.server.entitiy.Table> table;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
