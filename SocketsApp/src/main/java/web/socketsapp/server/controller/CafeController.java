package web.socketsapp.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import web.socketsapp.server.controller.dto.Mapper;
import web.socketsapp.server.controller.payload.FreeTablePayload;
import web.socketsapp.server.entitiy.Table;
import web.socketsapp.server.repository.HallRepo;
import web.socketsapp.server.repository.TableRepo;

import java.util.List;

@Controller
public class CafeController {
    private final HallRepo hallRepo;
    private final TableRepo tableRepo;

    @Autowired
    public CafeController(HallRepo hallRepo, TableRepo tableRepo) {
        this.hallRepo = hallRepo;
        this.tableRepo = tableRepo;
    }

    @MessageMapping("freeTables")
    public List<FreeTablePayload> freeTables() {
        List<Table> tables = tableRepo.findAll().stream().filter(t -> t.getTimetables() == null).toList();

        return tables.stream()
                .map(
                        t -> new FreeTablePayload(
                                t.getId(),
                                t.getNumber(),
                                t.getHall().getPrice(),
                                t.getHall().getName())
                ).toList();
    }
}
