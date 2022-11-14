package web.socketsapp.server.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HallDto {

    private Long id;
    private String name;
    private int price;

    private List<TableDto> tables;
}
