package web.socketsapp.server.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableDto {

    private Long id;
    private int number;

    private Long hall_id;
}
