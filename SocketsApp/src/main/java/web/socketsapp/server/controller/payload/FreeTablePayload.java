package web.socketsapp.server.controller.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import web.socketsapp.server.entitiy.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreeTablePayload {
    private long id;
    private int number;

    private int price;
    private String hall;

}
