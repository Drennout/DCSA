package web.socketsapp.server.controller.dto;

import web.socketsapp.server.entitiy.Hall;
import web.socketsapp.server.entitiy.Table;

public class Mapper {
    public static TableDto tableToDto(Table table) {
        return new TableDto(table.getId(), table.getNumber(), table.getHall().getId());
    }

    public static HallDto hallToDto(Hall hall) {
        return new HallDto
                (
                        hall.getId()
                        , hall.getName()
                        , hall.getPrice()
                        , hall.getTables().stream().map(Mapper::tableToDto).toList()
                );
    }
}
